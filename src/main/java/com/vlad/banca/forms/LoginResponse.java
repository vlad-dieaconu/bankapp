package com.vlad.banca.forms;

public class LoginResponse {

    private String email;
    private Long id;

    public LoginResponse(String email, Long id) {
        this.email = email;
        this.id = id;
    }
}
