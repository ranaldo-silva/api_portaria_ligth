package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Morador;
import com.fiap.portaria.service.MoradorService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/moradores")
@CrossOrigin("*")
public class MoradorController {
    private final MoradorService service;

    public MoradorController(MoradorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Morador> listar() {
        return service.listar();
    }

    @PostMapping
    public Morador salvar(@RequestBody Morador m) {
        return service.salvar(m);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}