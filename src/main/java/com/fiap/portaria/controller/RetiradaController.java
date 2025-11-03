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
    public ResponseEntity<Retirada> salvar(@RequestBody Retirada r) {
        // 🚨 Verificação de segurança — evita nulos
        if (r == null || r.getMorador() == null || r.getEncomenda() == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Retirada salva = service.salvar(r);
        // ✅ Retorna corpo JSON correto (não vazio)
        return ResponseEntity.ok(salva);
    }
}
