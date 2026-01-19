package com.ga.airticketmanagement.dto.request;
import com.ga.airticketmanagement.model.UserProfile;
import com.ga.airticketmanagement.model.Role;

public record RegisterRequest(
        String emailAddress,
        String password,
        Role role,
        UserProfile userProfile
) {}