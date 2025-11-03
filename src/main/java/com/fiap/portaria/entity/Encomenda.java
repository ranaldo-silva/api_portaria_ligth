package com.fiap.portaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Random;

@Entity
@Data
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String origem;
    private LocalDateTime dataRecebimento;
    private boolean retirada = false;
    private LocalDateTime retiradaEm;

    @ManyToOne
    private Morador morador;

    @PrePersist
    public void prePersist() {
        // ✅ Gera token numérico de 6 dígitos se não existir
        if (this.token == null || this.token.isBlank()) {
            Random random = new Random();
            int numero = 100000 + random.nextInt(900000);
            this.token = String.valueOf(numero);
        }

        // ✅ Define data de recebimento com fuso horário do Brasil
        if (this.dataRecebimento == null) {
            this.dataRecebimento = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        }
    }
}
