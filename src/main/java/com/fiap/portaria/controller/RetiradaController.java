package com.fiap.portaria.controller;

import com.fiap.portaria.entity.Retirada;
import com.fiap.portaria.service.RetiradaService;
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
    public Retirada salvar(@RequestBody Retirada r) {
        return service.salvar(r);
    }
}
