package com.fiap.portaria.service;

import com.fiap.portaria.entity.Retirada;
import com.fiap.portaria.repository.RetiradaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RetiradaService {
    private final RetiradaRepository repo;

    public RetiradaService(RetiradaRepository repo) {
        this.repo = repo;
    }

    public List<Retirada> listar() {
        return repo.findAll();
    }

    public Retirada salvar(Retirada r) {
        return repo.save(r);
    }
}
