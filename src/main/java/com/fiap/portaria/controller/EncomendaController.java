package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Encomenda;
import com.fiap.portaria.service.EncomendaService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/encomendas")
@CrossOrigin("*")
public class EncomendaController {
    private final EncomendaService service;

    public EncomendaController(EncomendaService service) {
        this.service = service;
    }

    @GetMapping
    public List<Encomenda> listar() {
        return service.listar();
    }

    @PostMapping
    public Encomenda salvar(@RequestBody Encomenda e) {
        return service.salvar(e);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }

    @GetMapping("/token/{token}")
    public Encomenda buscarPorToken(@PathVariable String token) {
        return service.buscarPorToken(token);
    }
}