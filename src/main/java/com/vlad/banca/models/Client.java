package com.vlad.banca.models;


import javax.persistence.*;

@Entity
@Table(name="client")
public class Client {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String CNP;
    private String nume;
    private String prenume;
    private double CONT_EURO = 0;
    private double CONT_RON = 0;
    private String email;
    private String password;



    public Client() {

    }

    public Client(String CNP, String nume,String prenume, String email, String password) {
        this.CNP = CNP;
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.password = password;
        this.CONT_EURO = this.getCONT_EURO();
        this.CONT_RON = this.getCONT_RON();

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getCNP() {
        return CNP;
    }

    public Long getId() {
        return id;
    }

    public void setCNP(String CNP) {
        this.CNP = CNP;
    }

    public double getCONT_EURO() {
        return CONT_EURO;
    }

    public void setCONT_EURO(double CONT_EURO) {
        this.CONT_EURO = CONT_EURO;
    }

    public double getCONT_RON() {
        return CONT_RON;
    }

    public void setCONT_RON(double CONT_RON) {
        this.CONT_RON = CONT_RON;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id='" + id + '\'' +
                ", CNP='" + CNP + '\'' +
                ", CONT_EURO=" + CONT_EURO +
                ", CONT_RON=" + CONT_RON +
                '}';
    }


}
