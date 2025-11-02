package com.fiap.portaria.repository;

import com.fiap.portaria.entity.Encomenda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EncomendaRepository extends JpaRepository<Encomenda, Long> {
    Encomenda findByToken(String token);
}