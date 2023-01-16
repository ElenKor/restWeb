package com.example.demo.models.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ADMIN, MANAGER, CHIEF, REGISTERED;

    @Override
    public String getAuthority() {
        return name();
    }
}
