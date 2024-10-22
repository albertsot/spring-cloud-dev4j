package com.umax.ms_security.model.dto;

public class TokenDTO {
    private String token;
    public TokenDTO() {

    }
    public TokenDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
