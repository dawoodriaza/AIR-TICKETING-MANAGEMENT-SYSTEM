package com.ga.airticketmanagement.controller;



import com.ga.airticketmanagement.service.WhatsAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    @Value("${twilio.sid}")
    private String twilioSid;

    @Value("${twilio.token}")
    private String twilioToken;

    @Value("${twilio.whatsappFrom}")
    private String whatsappFrom;

    private final WhatsAppService whatsAppService;

    @GetMapping("/config")
    public Map<String, String> testConfig() {
        Map<String, String> config = new HashMap<>();
        config.put("twilioSid", twilioSid != null ? "Set (length: " + twilioSid.length() + ")" : "NOT SET");
        config.put("twilioToken", twilioToken != null ? "Set (length: " + twilioToken.length() + ")" : "NOT SET");
        config.put("whatsappFrom", whatsappFrom != null ? whatsappFrom : "NOT SET");
        return config;
    }

    @PostMapping("/whatsapp")
    public String testWhatsApp(@RequestParam String phone, @RequestParam String message) {
        try {
            whatsAppService.send(phone, message, "TEST", null, null, null);
            return "WhatsApp test sent successfully";
        } catch (Exception e) {
            return "WhatsApp test failed: " + e.getMessage();
        }
    }
}
