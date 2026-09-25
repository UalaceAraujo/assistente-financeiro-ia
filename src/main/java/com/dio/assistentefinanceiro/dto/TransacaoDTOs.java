package com.dio.assistentefinanceiro.dto;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import java.math.BigDecimal;

public class TransacaoDTOs {

    public record TransacaoRequest(
        @JsonPropertyDescription("Descrição objetiva da despesa ou receita") String descricao,
        @JsonPropertyDescription("Valor numérico da transação") BigDecimal valor,
        @JsonPropertyDescription("Tipo da operação: RECEITA ou DESPESA") String tipo,
        @JsonPropertyDescription("Categoria estimada: Alimentação, Transporte, Lazer, etc.") String categoria
    ) {}

    public record TransacaoResponse(String status, Long transacaoId, String mensagem) {}
}