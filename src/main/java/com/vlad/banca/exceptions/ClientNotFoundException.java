package com.vlad.banca.exceptions;

public class ClientNotFoundException extends RuntimeException{
    public ClientNotFoundException(Long id) {
        super("Could not find client " + id);
    }
}