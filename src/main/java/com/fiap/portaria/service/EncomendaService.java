package com.fiap.portaria.service;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.repository.EncomendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaService {
    private final EncomendaRepository repo;

    public EncomendaService(EncomendaRepository repo) {
        this.repo = repo;
    }

    public List<Encomenda> listar() {
        return repo.findAll();
    }

    public Encomenda salvar(Encomenda e) {
        return repo.save(e);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public Encomenda buscarPorToken(String token) {
        return repo.findByToken(token);
    }
}