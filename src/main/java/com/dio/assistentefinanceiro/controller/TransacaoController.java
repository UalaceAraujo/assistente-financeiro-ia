package com.dio.assistentefinanceiro.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dio.assistentefinanceiro.model.Transacao;
import com.dio.assistentefinanceiro.repository.TransacaoRepository;
import com.dio.assistentefinanceiro.service.AssistenteVozService;

@RestController
@RequestMapping("/api/transacoes")
public class TransacaoController {

    private final AssistenteVozService assistente;
    private final TransacaoRepository transacaoRepository;

    public TransacaoController(AssistenteVozService assistente, TransacaoRepository transacaoRepository) {
        this.assistente = assistente;
        this.transacaoRepository = transacaoRepository;
    }

    @PostMapping(value = "/audio", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Map<String, String>> processarAudio(@RequestParam("audio") MultipartFile audio) {
        try {
            if (audio.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("erro", "Arquivo de áudio não enviado"));
            }
            String resposta = assistente.processarComandoAudio(audio);
            return ResponseEntity.ok(Map.of("resposta", resposta));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(Map.of("erro", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Transacao>> listarTodas() {
        return ResponseEntity.ok(transacaoRepository.findAll());
    }
}