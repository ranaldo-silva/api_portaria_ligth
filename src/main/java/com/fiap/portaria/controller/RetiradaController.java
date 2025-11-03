package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.entity.Retirada;
import com.fiap.portaria.repository.EncomendaRepository;
import com.fiap.portaria.service.RetiradaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

        // ✅ Verifica campos obrigatórios
        if (r.getMorador() == null || r.getEncomenda() == null) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Campos morador/encomenda estão nulos\"}");
        }

        try {
            // ✅ Busca a encomenda pelo token e marca como retirada
            Encomenda e = encomendaRepo.findByToken(r.getEncomenda());
            if (e != null) {
                e.setRetirada(true);
                e.setRetiradaEm(LocalDateTime.now());
                encomendaRepo.save(e);
                System.out.println("✅ Encomenda marcada como retirada: " + e.getToken());
            } else {
                System.out.println("⚠️ Nenhuma encomenda encontrada com token: " + r.getEncomenda());
            }

            // ✅ Salva registro da retirada
            Retirada salva = service.salvar(r);
            return ResponseEntity.ok(salva);

        } catch (Exception ex) {
            System.out.println("❌ Erro ao processar retirada: " + ex.getMessage());
            return ResponseEntity.internalServerError()
                    .body("{\"erro\":\"Falha ao registrar retirada\"}");
        }
    }
}
