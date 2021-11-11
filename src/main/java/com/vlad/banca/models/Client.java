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
    private double CONT_EURO;
    private double CONT_RON;


    public Client() {

    }

    public Client(String CNP, double CONT_EURO, double CONT_RON, String nume) {
        this.CNP = CNP;
        this.CONT_EURO = CONT_EURO;
        this.CONT_RON = CONT_RON;
        this.nume = nume;
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
