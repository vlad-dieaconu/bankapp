package com.vlad.banca.models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="fisc")
public class Fisc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idClient;




    public Fisc(){}

    public Fisc(Long idClient) {
        this.idClient = idClient;
    }


    public Long getIdClient() {
        return idClient;
    }

    public Long idClient() {
        return idClient;
    }

    public void setClientMonitorizat(Long clientMonitorizat) {
        this.idClient = idClient;
    }



    @Override
    public String toString() {
        return "Fisc{" +
                "clientMonitorizat='" + idClient + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }
}
