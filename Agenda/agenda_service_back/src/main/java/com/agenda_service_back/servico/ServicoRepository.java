package com.agenda_service_back.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ServicoRepository extends JpaRepository<Servico, Long> {
    @Query("SELECT s FROM Servico s WHERE s.servicoNome = :servicoName")
    List<Servico> findByServicoNome(@Param("servicoName") String servicoName);
}
