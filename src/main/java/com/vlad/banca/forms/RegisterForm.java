package com.vlad.banca.forms;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class RegisterForm {

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nume;
    @NotBlank
    private String prenume;
    @NotBlank
    private String CNP;

    @NotBlank
    @Size(min=5,max=20)
    private String password;



    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passsowrd) {
        this.password = passsowrd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
}
