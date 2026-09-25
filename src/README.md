# Assistente Financeiro Inteligente com Spring AI

API REST desenvolvida em Spring Boot para o processamento de transações financeiras orientadas por comandos de voz. A solução integra transcrição de áudio via Speech-to-Text (STT) e modelos de linguagem para orquestração de operações transacionais por meio do padrão Tool Calling (Function Calling).

---

## Arquitetura da Solução

O fluxo operacional da aplicação está estruturado nas seguintes etapas:

1. **Entrada de Áudio:** O endpoint REST recebe o arquivo multimídia enviado pelo cliente via requisição multipart.
2. **Transcrição (STT):** O modelo Whisper processa o ficheiro e converte a voz em texto com suporte a termos monetários em português.
3. **Interpretação e Tool Calling:** O componente ChatClient do Spring AI avalia o texto transcrito, identifica parâmetros essenciais (valor, categoria, tipo e descrição) e invoca a função Java correspondente.
4. **Camada Transacional e Persistência:** A regra de negócio valida o comando e persiste a entidade na base de dados relacional via Spring Data JPA.
5. **Retorno Operacional:** A resposta textual consolidada é devolvida ao cliente em formato JSON.

---

## Tecnologias Utilizadas

* **Java 21**
* **Spring Boot 3.4.2**
* **Spring AI (Whisper e ChatClient com Function Calling)**
* **Spring Data JPA**
* **H2 Database** (Base de dados relacional em memória)
* **Maven**

---

## Execução Local

### Pré-requisitos
* Java Development Kit (JDK) 21 instalado e configurado no PATH.
* Chave de autenticação da API (OpenAI ou Groq).

### Configuração de Credenciais
Defina a variável de ambiente no terminal:

```cmd
set OPENAI_API_KEY=sua_chave_aqui