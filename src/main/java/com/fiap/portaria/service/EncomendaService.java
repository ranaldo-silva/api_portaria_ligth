package com.fiap.portaria.service;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.entity.Morador;
import com.fiap.portaria.repository.EncomendaRepository;
import com.fiap.portaria.repository.MoradorRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

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
        // 🔍 Garante que o morador realmente existe e carrega completo
        if (e.getMorador() != null && e.getMorador().getId() != null) {
            Optional<Morador> m = moradorRepo.findById(e.getMorador().getId());
            m.ifPresent(e::setMorador);
        }

        // 🔧 Garante data de recebimento correta (fuso Brasil)
        if (e.getDataRecebimento() == null) {
            e.setDataRecebimento(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));
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
