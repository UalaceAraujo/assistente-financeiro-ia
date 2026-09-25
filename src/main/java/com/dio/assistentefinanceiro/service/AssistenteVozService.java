package com.dio.assistentefinanceiro.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiAudioTranscriptionModel;
import org.springframework.ai.audio.transcription.AudioTranscriptionPrompt;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
public class AssistenteVozService {

    private final OpenAiAudioTranscriptionModel transcriptionModel;
    private final ChatClient chatClient;

    public AssistenteVozService(OpenAiAudioTranscriptionModel transcriptionModel, ChatClient.Builder chatBuilder) {
        this.transcriptionModel = transcriptionModel;
        this.chatClient = chatBuilder.build();
    }

    public String processarComandoAudio(MultipartFile audio) throws IOException {
        var resource = new ByteArrayResource(audio.getBytes()) {
            @Override
            public String getFilename() {
                return audio.getOriginalFilename() != null ? audio.getOriginalFilename() : "audio.wav";
            }
        };

        var audioPrompt = new AudioTranscriptionPrompt(resource);
        String texto = transcriptionModel.call(audioPrompt).getResult().getOutput();

        return chatClient.prompt()
            .system("""
                Você é um assistente financeiro pessoal conciso.
                Identifique gastos ou receitas relatados pelo usuário.
                Quando identificar uma transação, chame a ferramenta 'registrarTransacao'.
                Ao final, responda confirmando o valor, o item e a categoria registrada.
            """)
            .user(texto)
            .functions("registrarTransacao")
            .call()
            .content();
    }
}