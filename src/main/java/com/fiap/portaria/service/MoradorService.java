package com.fiap.portaria.service;

import com.fiap.portaria.entity.Morador;
import com.fiap.portaria.repository.MoradorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MoradorService {
    private final MoradorRepository repo;

    public MoradorService(MoradorRepository repo) {
        this.repo = repo;
    }

    public List<Morador> listar() {
        return repo.findAll();
    }

    public Morador salvar(Morador m) {
        return repo.save(m);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }
}