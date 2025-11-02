package com.fiap.portaria.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class Encomenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String origem;
    private LocalDateTime dataRecebimento;
    private boolean retirada;
    private LocalDateTime retiradaEm;

    @ManyToOne
    private Morador morador;
}