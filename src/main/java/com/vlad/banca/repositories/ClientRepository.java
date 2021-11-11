package com.vlad.banca.repositories;

import com.vlad.banca.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCNP(String CNP);
    Client findByemail(String email);
    Boolean existsByemail(String email);
    Boolean existsByCNP(String cnp);


}
