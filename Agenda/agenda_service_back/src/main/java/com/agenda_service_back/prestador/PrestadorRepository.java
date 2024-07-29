package com.agenda_service_back.prestador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrestadorRepository extends JpaRepository<Prestador, Long> {
    Prestador findByPrestadorEmail(String prestadorEmail);
}
