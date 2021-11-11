package com.vlad.banca.controllers;

import com.vlad.banca.exceptions.ClientMonitorizatNotFoundException;
import com.vlad.banca.models.Client;
import com.vlad.banca.models.Fisc;
import com.vlad.banca.repositories.ClientRepository;
import com.vlad.banca.repositories.FiscRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vlad.banca.exceptions.ClientNotFoundException;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(path = "/fisc")
public class FiscController {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private FiscRepository fiscRepository;


    @PostMapping("/addclientmonitorizat")
    public Fisc addNewClient(@RequestParam Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new ClientNotFoundException(idClient));
        Long clientMonitorizat = client.getId();

        Fisc fisc = new Fisc(clientMonitorizat);

        fiscRepository.save(fisc);

        return fisc;

    }

    @DeleteMapping("/deleteclient")
    @Transactional
    public void deleteClient(@RequestParam Long idClient){
        Client client = clientRepository.findById(idClient).orElseThrow(() -> new ClientNotFoundException(idClient));
        Long clientMonitorizat = client.getId();
        if(fiscRepository.existsByidClient(clientMonitorizat)){
            Fisc fisc = fiscRepository.findByidClient(clientMonitorizat);
            Long id = fisc.getId();
            fiscRepository.deleteById(id);
        }else throw new ClientMonitorizatNotFoundException(clientMonitorizat);
    }

    @GetMapping("/getclientsdata")
    public List<Optional<Client>> getClientsData(){
        List<Optional<Client>> clients = new ArrayList();
        List<Fisc> listaClienti = new ArrayList<>();

        listaClienti = fiscRepository.findAll();

        ArrayList<Long> clientsIds = new ArrayList<Long>();

        for(int i = 0; i < listaClienti.size(); i++){
            clientsIds.add(listaClienti.get(i).getIdClient());
        }

        for(int i = 0; i < clientsIds.size(); i++){
            Optional<Client> client = clientRepository.findById(clientsIds.get(i));
            clients.add(client);

        }

        return clients;
    }



    




}

