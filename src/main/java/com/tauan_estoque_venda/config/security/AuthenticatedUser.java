package com.tauan_estoque_venda.config.security;

import com.tauan_estoque_venda.dtos.JWTUserData;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticatedUser {
    public static JWTUserData get(){
        return (JWTUserData) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
