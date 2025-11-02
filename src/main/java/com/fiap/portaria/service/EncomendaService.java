package com.fiap.portaria.service;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.entity.Morador;
import com.fiap.portaria.repository.EncomendaRepository;
import com.fiap.portaria.repository.MoradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EncomendaService {
    private final EncomendaRepository repo;
    private final MoradorRepository moradorRepo;

    public EncomendaService(EncomendaRepository repo, MoradorRepository moradorRepo) {
        this.repo = repo;
        this.moradorRepo = moradorRepo;
    }

    public List<Encomenda> listar() {
        return repo.findAll();
    }

    public Encomenda salvar(Encomenda e) {
        // ✅ Garante que o morador seja carregado corretamente
        if (e.getMorador() != null && e.getMorador().getId() != null) {
            Morador morador = moradorRepo.findById(e.getMorador().getId()).orElse(null);
            e.setMorador(morador);
        }

        return repo.save(e);
    }

    public void excluir(Long id) {
        repo.deleteById(id);
    }

    public Encomenda buscarPorToken(String token) {
        return repo.findByToken(token);
    }
}
