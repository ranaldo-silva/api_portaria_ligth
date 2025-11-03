package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Retirada;
import com.fiap.portaria.service.RetiradaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/retiradas")
@CrossOrigin("*")
public class RetiradaController {

    private final RetiradaService service;

    public RetiradaController(RetiradaService service) {
        this.service = service;
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

        // Garantir que campos não sejam nulos
        if (r.getMorador() == null || r.getEncomenda() == null) {
            return ResponseEntity.badRequest().body("{\"erro\":\"Campos morador/encomenda estão nulos\"}");
        }

        Retirada salva = service.salvar(r);

        // ✅ Retorna JSON corretamente, evitando resposta vazia
        return ResponseEntity.ok(salva);
    }
}
