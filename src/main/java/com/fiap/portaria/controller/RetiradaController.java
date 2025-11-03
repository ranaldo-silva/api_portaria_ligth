package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.entity.Retirada;
import com.fiap.portaria.repository.EncomendaRepository;
import com.fiap.portaria.service.RetiradaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@RestController
@RequestMapping("/retiradas")
@CrossOrigin("*")
public class RetiradaController {

    private final RetiradaService service;
    private final EncomendaRepository encomendaRepo;

    public RetiradaController(RetiradaService service, EncomendaRepository encomendaRepo) {
        this.service = service;
        this.encomendaRepo = encomendaRepo;
    }

    @GetMapping
    public List<Retirada> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<?> salvar(@RequestBody Retirada r) {
        if (r == null) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Corpo da requisição vazio\"}");
        }

        System.out.println("📦 Recebido no backend: " + r);

        if (r.getMorador() == null || r.getEncomenda() == null) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Campos morador/encomenda estão nulos\"}");
        }

        try {
            // 🔎 Busca encomenda pelo token
            Encomenda e = encomendaRepo.findByToken(r.getEncomenda());
            if (e != null) {
                e.setRetirada(true);
                e.setRetiradaEm(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

                // ✅ Se a encomenda tiver morador vinculado, usar o nome completo
                if (e.getMorador() != null) {
                    String nomeCompleto = e.getMorador().getNome() + " " + e.getMorador().getSobrenome();
                    r.setMorador(nomeCompleto);
                } else if (r.getMorador() == null) {
                    r.setMorador("Desconhecido");
                }

                encomendaRepo.save(e);
                System.out.println("✅ Encomenda marcada como retirada: " + e.getToken());
            } else {
                System.out.println("⚠️ Nenhuma encomenda encontrada com token: " + r.getEncomenda());
                r.setMorador("Desconhecido");
            }

            // ✅ Hora de retirada ajustada (fuso Brasil)
            r.setDataRetirada(LocalDateTime.now(ZoneId.of("America/Sao_Paulo")));

            Retirada salva = service.salvar(r);
            return ResponseEntity.ok(salva);

        } catch (Exception ex) {
            System.out.println("❌ Erro ao processar retirada: " + ex.getMessage());
            return ResponseEntity.internalServerError()
                    .body("{\"erro\":\"Falha ao registrar retirada\"}");
        }
    }
}
