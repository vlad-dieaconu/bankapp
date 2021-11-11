package com.vlad.banca.controllers;

import com.vlad.banca.forms.LoginForm;
import com.vlad.banca.forms.LoginResponse;
import com.vlad.banca.forms.RegisterForm;
import com.vlad.banca.models.Client;
import com.vlad.banca.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import com.vlad.banca.exceptions.ClientNotFoundException;


import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "/client")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/login")
    public ResponseEntity loginClient(@Valid @RequestBody LoginForm loginForm){

        if(loginForm.getEmail().equals("fisc@mail.com") && loginForm.getPassword().equals("fisc"))
            return ResponseEntity.ok().body("FISC");

        if(clientRepository.existsByemail(loginForm.getEmail())){
        Client client = clientRepository.findByemail(loginForm.getEmail());


        if(client.getPassword().equals(loginForm.getPassword()))
           return ResponseEntity.ok().body(client);

        } else
            return new ResponseEntity("Login fail !",
                    HttpStatus.BAD_REQUEST);
        return ResponseEntity.ok().body("LOGIN FAIL");
    }

    @PostMapping("/register")
    public ResponseEntity registerClient(@Valid @RequestBody RegisterForm registerForm){
        System.out.println("AM INTRAT AICI");
        if(clientRepository.existsByemail(registerForm.getEmail()) || clientRepository.existsByemail(registerForm.getCNP())){
            return new ResponseEntity("Fail -> Email is already taken!",
                    HttpStatus.BAD_REQUEST);
        }

        System.out.println("CEVA");
        Client client = new Client (registerForm.getCNP(),registerForm.getNume(),registerForm.getPrenume()
                ,registerForm.getEmail(), registerForm.getPassword());

        clientRepository.save(client);
       // return client;
        return ResponseEntity.ok().body("Client registered successfully !");

    }

    @GetMapping("/getdata/{id}")
    public Client getClientData(@PathVariable Long id){

        Client client = clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException(id));
        return client;

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






