package com.fiap.portaria.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Table(name = "retiradas")
public class Retirada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String morador;
    private String encomenda;

    private LocalDateTime dataRetirada;

    public Retirada() {
    }

    public Retirada(String morador, String encomenda) {
        this.morador = morador;
        this.encomenda = encomenda;
        // 🔧 Ajuste: usa ZonedDateTime para aplicar fuso corretamente
        ZonedDateTime agoraSP = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        this.dataRetirada = agoraSP.toLocalDateTime();
    }

    @PrePersist
    public void prePersist() {
        if (this.dataRetirada == null) {
            ZonedDateTime agoraSP = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
            this.dataRetirada = agoraSP.toLocalDateTime();
        }
    }

    // Getters e setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMorador() {
        return morador;
    }

    public void setMorador(String morador) {
        this.morador = morador;
    }

    public String getEncomenda() {
        return encomenda;
    }

    public void setEncomenda(String encomenda) {
        this.encomenda = encomenda;
    }

    public LocalDateTime getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDateTime dataRetirada) {
        this.dataRetirada = dataRetirada;
    }
}
