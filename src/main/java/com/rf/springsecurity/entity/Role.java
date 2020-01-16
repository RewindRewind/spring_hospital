package com.rf.springsecurity.entity;

import org.springframework.security.core.GrantedAuthority;


public enum Role implements GrantedAuthority {
    ROLE_RECEPTIONIST, ROLE_DOCTOR, ROLE_NURSE, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
