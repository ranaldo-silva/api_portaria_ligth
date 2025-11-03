package com.fiap.portaria.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Random;

@Entity
@Data
public class Encomenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;
    private String origem;
    private LocalDateTime dataRecebimento = LocalDateTime.now();
    private boolean retirada = false;
    private LocalDateTime retiradaEm;

    @ManyToOne
    private Morador morador;

    @PrePersist
    public void gerarToken() {
        if (this.token == null || this.token.isBlank()) {
            // ✅ Gera token numérico de 6 dígitos
            Random random = new Random();
            int numero = 100000 + random.nextInt(900000);
            this.token = String.valueOf(numero);
        }

        // Garante que a data de recebimento seja registrada
        if (this.dataRecebimento == null) {
            this.dataRecebimento = LocalDateTime.now();
        }
    }
}
