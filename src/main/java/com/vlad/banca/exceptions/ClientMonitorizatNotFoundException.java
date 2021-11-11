package com.vlad.banca.exceptions;

public class ClientMonitorizatNotFoundException extends RuntimeException{
    public ClientMonitorizatNotFoundException(Long id) {
        super("Could not find client " + id);
    }
}

