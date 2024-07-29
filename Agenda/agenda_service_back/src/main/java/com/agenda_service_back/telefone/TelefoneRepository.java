package com.agenda_service_back.telefone;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepository extends JpaRepository<Telefone,Long> {
    //cria uma interface para ser implementada
}
