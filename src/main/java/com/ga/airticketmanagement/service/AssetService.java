package com.ga.airticketmanagement.service;

import com.ga.airticketmanagement.exception.InformationNotFoundException;
import com.ga.airticketmanagement.model.Asset;
import com.ga.airticketmanagement.model.User;
import com.ga.airticketmanagement.repository.AssetRepository;
import com.ga.airticketmanagement.security.AuthenticatedUserProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;


@Service
public class AssetService {

    private static final Logger logger = LoggerFactory.getLogger(AssetService.class);

    @Autowired
    private AssetRepository assetRepository;


    @Value("${file.upload-dir}")
    private String uploadDir;
    @Autowired
    private AuthenticatedUserProvider authenticatedUserProvider;


    private Path resolveUploadDirectory() {
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isWindows = osName.contains("win");
        
        Path uploadDirectoryPath;
        
        if (uploadDir.startsWith("/") && !uploadDir.startsWith("//")) {
            if (isWindows) {
                String projectRoot = System.getProperty("user.dir");
                uploadDirectoryPath = Paths.get(projectRoot, uploadDir.substring(1));
            } else {
                uploadDirectoryPath = Paths.get(uploadDir);
            }
        } else if (uploadDir.contains(":\\") || uploadDir.startsWith("\\\\")) {
            uploadDirectoryPath = Paths.get(uploadDir);
        } else {
            String projectRoot = System.getProperty("user.dir");
            uploadDirectoryPath = Paths.get(projectRoot, uploadDir);
        }
        
        uploadDirectoryPath = uploadDirectoryPath.normalize().toAbsolutePath();
        logger.info("Resolved upload directory: {} (OS: {})", uploadDirectoryPath, osName);
        return uploadDirectoryPath;
    }

    private Path resolveFilePath(String storedFilePath) {
        if (storedFilePath == null || storedFilePath.isBlank()) {
            return null;
        }
        
        String osName = System.getProperty("os.name").toLowerCase();
        boolean isWindows = osName.contains("win");
        String normalizedPath = storedFilePath.replace("\\", "/");
        Path path;
        
        if (normalizedPath.matches("^[A-Za-z]:/.*") || storedFilePath.contains(":\\")) {
            path = Paths.get(storedFilePath);
        } else if (normalizedPath.startsWith("/")) {
            if (isWindows) {
                String projectRoot = System.getProperty("user.dir");
                path = Paths.get(projectRoot, normalizedPath.substring(1));
            } else {
                path = Paths.get(normalizedPath);
            }
        } else {
            path = Paths.get(storedFilePath);
            if (!Files.exists(path)) {
                Path uploadDirPath = resolveUploadDirectory();
                String fileName = Paths.get(normalizedPath).getFileName().toString();
                Path alternativePath = uploadDirPath.resolve(fileName);
                if (Files.exists(alternativePath)) {
                    return alternativePath.normalize().toAbsolutePath();
                }
            }
        }
        
        return path.normalize().toAbsolutePath();
    }


    public Asset saveImage(MultipartFile file, Long userId) throws IOException {
        User user = authenticatedUserProvider.getAuthenticatedUser();
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is empty or null");
        }

        String originalFileName = file.getOriginalFilename();
        if (originalFileName == null || originalFileName.isBlank()) {
            throw new IllegalArgumentException("File name is null or empty");
        }

        String contentType = file.getContentType();
        long fileSize = file.getSize();

        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        Path uploadDirectoryPath = resolveUploadDirectory();

        try {
            Files.createDirectories(uploadDirectoryPath);
            logger.info("Upload directory ready: {}", uploadDirectoryPath);
        } catch (IOException e) {
            logger.error("Failed to create upload directory: {}", uploadDirectoryPath, e);
            throw new IOException("Failed to create upload directory: " + uploadDirectoryPath, e);
        }

        if (!Files.isWritable(uploadDirectoryPath)) {
            throw new IOException("Upload directory is not writable: " + uploadDirectoryPath);
        }

        Path filePath = uploadDirectoryPath.resolve(uniqueFileName);
        logger.info("Saving file to: {}", filePath);

        try {
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File saved successfully: {}", filePath);
        } catch (IOException e) {
            logger.error("Failed to save file to: {}", filePath, e);
            throw new IOException("Failed to save file: " + e.getMessage(), e);
        }

        if (!Files.exists(filePath)) {
            throw new IOException("File was not saved. Path: " + filePath);
        }

        Asset asset = new Asset();
        asset.setFileName(uniqueFileName);
        asset.setOriginalFileName(originalFileName);
        asset.setFileType(contentType);
        asset.setFileSize(fileSize);
        asset.setFilePath(filePath.toString().replace("\\", "/"));
        asset.setUser(user);

        Asset savedEntity = assetRepository.save(asset);
        logger.info("ImageEntity saved to database with ID: {}", savedEntity.getId());
        
        return savedEntity;
    }


    public Asset getImageByFileName(String fileName) {
        if (fileName == null) {
            throw new IllegalArgumentException("Request body is missing");
        }
        return assetRepository.findByFileName(fileName).orElse(null);
    }


    public List<Asset> getAllImages() {

        return assetRepository.findAll();
    }


    public boolean deleteImage(Long id) {

        Asset asset = assetRepository.findById(id).orElseThrow(()-> new InformationNotFoundException ("imageEntity with Id " + id + " not found"));

        try {
            Path path = resolveFilePath(asset.getFilePath());
            
            if (path != null && Files.exists(path)) {
                Files.deleteIfExists(path);
                logger.info("Deleted image file: {}", path);
            } else {
                logger.warn("Image file not found for deletion: {}", asset.getFilePath());
            }
        } catch (IOException e) {
            logger.error("Failed to delete image file: {}", asset.getFilePath(), e);
            e.printStackTrace();
        }
        
        assetRepository.deleteById(id);

        return true;
    }


    public byte[] getImageFile(String fileName) throws IOException {

        Asset image = getImageByFileName(fileName);

        if (image == null) {
            return null;
        }

        Path path = resolveFilePath(image.getFilePath());
        
        if (path == null || !Files.exists(path)) {
            logger.warn("Image file not found at path: {}", image.getFilePath());
            return null;
        }

        return Files.readAllBytes(path);
    }
}