
# 📌 Desafio 2 | Grupo ZeroUm - Sistema de Microserviços com Spring Boot

Projeto com Microserviços A e B para gerenciamento de postagens e comentários usando Spring Boot, FeignClient e JSONPlaceholder como API externa.

Este projeto implementa dois microserviços em Spring Boot:

Microserviço A: Serviço responsável por receber as requisições do cliente.
Microserviço B: Responsável por receber as requisições do serviço A e gerenciar os dados das postagens via JSONPlaceholder.
Os serviços se comunicam via FeignClient, e a API é documentada com Swagger.


# 👤 Autores

- Daniel Oliveira de Jezus | [@Daniel20912](https://github.com/Daniel20912)
- Henrique Jornada Martinuzzi | [@jornadev](https://github.com/jornadev)
- Hygor Lorençato da Conceição | [@Hygorlc](https://github.com/Hygorlc)
- Karollyne Evangelista Moreira | [@KarollyneDev](https://github.com/KarollyneDev)
- Luis Eduardo Souza Teles | [@luixsouza](https://github.com/luixsouza)


# 🚀 Tecnologias Utilizadas
- Java 17
- Spring Boot 
- Feign cliente
- JSONPlaceholder
- Swagger
- H2

# 📌 Endpoints Disponíveis
🟢 Microserviço A (Porta 8080)
- GET /api/posts → Lista todas as postagens
- GET /api/posts/{id} → Lista uma postagem em específico
- POST /api/posts → Cria uma nova postagem
- PUT /api/posts/{id} → Atualiza uma postagem
- DELETE /api/posts/{id} → Remove uma postagem
- POST /api/posts/{id}/comments → Cria um novo comentário
- PUT /api/posts/{id}/comments/{id} → Atualiza um comentário
- DELETE /api/posts/{id}/comments/{id} - Remove um comentário

🔵 Microserviço B (Porta 8081)
- POST /api/sync-data





# Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/luixsouza/Desafio2_ZeroUm.git
```

Entre no diretório do projeto e abra na IDE de sua preferência (recomendado o Intellij)

```bash
  cd Desafio2_ZeroUm
```

Instale (ou atualize) as dependências caso necessário
 

Rodar microserviço B

```bash
    mvn spring-boot:run
```
Rodar microserviço A

```bash
    mvn spring-boot:run
```

Agora os microserviços estão rodando em:

- Microserviço A: http://localhost:8080
- Microserviço B: http://localhost:8081

Usando o Postman ou algum software para fazer as requisições, faça a requisição para consumir a API externa (http://localhost:8081/api/sync-data)

Feito isso você estará pronto para requirir as demais funcionalidades da aplicação.

