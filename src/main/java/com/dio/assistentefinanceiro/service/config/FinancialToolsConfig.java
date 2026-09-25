package com.dio.assistentefinanceiro.service.config;

import com.dio.assistentefinanceiro.dto.TransacaoDTOs.TransacaoRequest;
import com.dio.assistentefinanceiro.dto.TransacaoDTOs.TransacaoResponse;
import com.dio.assistentefinanceiro.service.TransacaoService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import java.util.function.Function;

@Configuration
public class FinancialToolsConfig {

    @Bean
    @Description("Registra uma despesa ou receita no sistema quando o usuário relatar uma transação financeira")
    public Function<TransacaoRequest, TransacaoResponse> registrarTransacao(TransacaoService service) {
        return request -> {
            var salva = service.salvar(request);
            return new TransacaoResponse("SUCESSO", salva.getId(), "Transação registrada com o ID " + salva.getId());
        };
    }
}