package com.vlad.banca.controllers;

import com.vlad.banca.models.Client;
import com.vlad.banca.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.vlad.banca.exceptions.ClientNotFoundException;
import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/add")
    Client postClient(@RequestBody Client client){
        return clientRepository.save(client);
    }

    @GetMapping("/getalldata")
    public List<Client> allClients(){
        return clientRepository.findAll();
    }

    @PutMapping("/retragere/conteuro/{id}")
    public Client retragereEuro(@PathVariable Long id, @RequestParam double sumaRetragere ){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double newBalance = client.getCONT_EURO() - sumaRetragere;
        if(newBalance > 1000){
            client.setCONT_EURO(newBalance);
        }else{
            client.setCONT_EURO(client.getCONT_EURO());
        }
        return clientRepository.save(client);
    }

    @PutMapping("/retragere/contron/{id}")
    public Client retragereRon(@PathVariable Long id, @RequestParam double sumaRetragere ){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double newBalance = client.getCONT_RON() - sumaRetragere;
        if(newBalance > 1000){
            client.setCONT_RON(newBalance);
        }else{
            client.setCONT_RON(client.getCONT_RON());
        }
        return clientRepository.save(client);
    }

    @PutMapping("/depunere/conteuro/{id}")
    public Client depunereEuro(@PathVariable Long id, @RequestParam double sumaDepunere ){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double newBalance = client.getCONT_EURO() + sumaDepunere;

        client.setCONT_EURO(newBalance);

        return clientRepository.save(client);
    }

    @PutMapping("/depunere/contron/{id}")
    public Client depunereRon(@PathVariable Long id, @RequestParam double sumaDepunere ){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double newBalance = client.getCONT_EURO() + sumaDepunere;

        client.setCONT_RON(newBalance);

        return clientRepository.save(client);
    }

    @GetMapping("/info/contron/{id}")
    public String getRonAccountInfo(@PathVariable Long id){

        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double balanceInRON = client.getCONT_RON();
        return "Your RON account blance: "+balanceInRON +".";

    }

    @GetMapping("/info/conteuro/{id}")
    public String getEuroAccountInfo(@PathVariable Long id){

        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        double balanceInEURO = client.getCONT_EURO();
        return "Your RON account blance: "+balanceInEURO +".";

    }

    @DeleteMapping("/deleteAccount/{id}")
    public void deleteClientAccount(@PathVariable Long id){
        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        //TODO de verificat "Soldul unui cont nu poate fi mai mic de 1000 RON/EURO." nu prea inteleg :(
        if(client.getCONT_RON() == 0 && client.getCONT_EURO() == 0)
            clientRepository.deleteById(client.getId());
    }


}






