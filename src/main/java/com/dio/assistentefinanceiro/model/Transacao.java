package com.dio.assistentefinanceiro.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descricao;
    private BigDecimal valor;
    private String tipo; // RECEITA ou DESPESA
    private String categoria;
    private LocalDateTime dataHora = LocalDateTime.now();

    public Transacao() {}

    public Transacao(String descricao, BigDecimal valor, String tipo, String categoria) {
        this.descricao = descricao;
        this.valor = valor;
        this.tipo = tipo;
        this.categoria = categoria;
    }

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public String getTipo() { return tipo; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getDataHora() { return dataHora; }
}