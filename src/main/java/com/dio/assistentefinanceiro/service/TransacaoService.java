package com.dio.assistentefinanceiro.service;

import com.dio.assistentefinanceiro.dto.TransacaoDTOs.TransacaoRequest;
import com.dio.assistentefinanceiro.model.Transacao;
import com.dio.assistentefinanceiro.repository.TransacaoRepository;
import org.springframework.stereotype.Service;

@Service
public class TransacaoService {

    private final TransacaoRepository repository;

    public TransacaoService(TransacaoRepository repository) {
        this.repository = repository;
    }

    public Transacao salvar(TransacaoRequest request) {
        Transacao transacao = new Transacao(
            request.descricao(),
            request.valor(),
            request.tipo() != null ? request.tipo().toUpperCase() : "DESPESA",
            request.categoria() != null ? request.categoria() : "Outros"
        );
        return repository.save(transacao);
    }
}