# 📌 Desafio 2 | Grupo ZeroUm - Sistema de Microserviços com Spring Boot

Projeto com Microserviços A e B para gerenciamento de postagens e comentários usando Spring Boot, FeignClient e JSONPlaceholder como API externa.

Este projeto implementa dois microserviços em Spring Boot:

- **Microserviço A**: Serviço responsável por receber as requisições do cliente.
- **Microserviço B**: Responsável por receber as requisições do serviço A e gerenciar os dados das postagens via JSONPlaceholder.

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
- Feign Client
- JSONPlaceholder
- Swagger
- H2

# 📌 Endpoints Disponíveis

🟢 **Microserviço A (Porta 8080)**

- **GET /api/posts** → Lista todas as postagens
  - **Exemplo de Request**:
    ```http
    GET http://localhost:8080/api/posts
    ```

- **GET /api/posts/{id}** → Lista uma postagem específica
  - **Exemplo de Request**:
    ```http
    GET http://localhost:8080/api/posts/1
    ```
  - **Exemplo de Resposta (JSON)**:
    ```json
    {
      "id": 1,
      "title": "Título da postagem",
      "body": "Conteúdo da postagem"
      "userId": 1
    }
    ```

- **POST /api/posts** → Cria uma nova postagem
  - **Exemplo de Request**:
    ```http
    POST http://localhost:8080/api/posts
    Content-Type: application/json
    ```
  - **Exemplo de Body (JSON)**:
    ```json
    {
      "title": "Nova postagem",
      "body": "Conteúdo da nova postagem"
      "userId": 1
    }
    ```
## ⬇️ Arquivo JSON com Endpoints para Importação no Postman

Para facilitar o teste dos endpoints, disponibilizamos um arquivo JSON formatado para importação direta no Postman (ou ferramentas similares). Este arquivo contém todas as configurações de requisição necessárias para interagir com os microserviços.

[**Download do arquivo JSON:** REST API basics- CRUD.postman_collection.json](https://github.com/luixsouza/Desafio2_ZeroUm/blob/main/docs/REST%20API%20basics%20-%20CRUD.postman_collection.json)


**Como importar no Postman:**

1.  Baixe o arquivo `REST API basics- CRUD.postman_collection.json` clicando no link acima.
2.  Abra o Postman.
3.  Clique no botão "Import" (geralmente no canto superior esquerdo).
4.  Escolha a opção "Import from File" e selecione o arquivo JSON que você baixou.

Após a importação, você terá uma nova Collection no Postman com todos os endpoints pré-configurados, prontos para serem utilizados.

- **PUT /api/posts/{id}** → Atualiza uma postagem
  - **Exemplo de Request**:
    ```http
    PUT http://localhost:8080/api/posts/1
    Content-Type: application/json
    ```
  - **Exemplo de Body (JSON)**:
    ```json
    {
      "title": "Título atualizado",
      "body": "Conteúdo da postagem atualizado"
      "userId": 1
    }
    ```

- **DELETE /api/posts/{id}** → Remove uma postagem
  - **Exemplo de Request**:
    ```http
    DELETE http://localhost:8080/api/posts/1
    ```

- **POST /api/posts/{id}/comments** → Cria um novo comentário
  - **Exemplo de Request**:
    ```http
    POST http://localhost:8080/api/posts/1/comments
    Content-Type: application/json
    ```
  - **Exemplo de Body (JSON)**:
    ```json
    {
      "name": "Comentário",
      "email": "exemplo@email.com",
      "body": "Conteúdo do comentário"
    }
    ```

- **PUT /api/posts/{id}/comments/{id}** → Atualiza um comentário
  - **Exemplo de Request**:
    ```http
    PUT http://localhost:8080/api/posts/1/comments/1
    Content-Type: application/json
    ```
  - **Exemplo de Body (JSON)**:
    ```json
    {
      "name": "Comentário atualizado",
      "email": "exemplo@email.com"
      "body": "Texto do comentário atualizado"
    }
    ```

- **DELETE /api/posts/{id}/comments/{id}** → Remove um comentário
  - **Exemplo de Request**:
    ```http
    DELETE http://localhost:8080/api/posts/1/comments/1
    ```

🔵 **Microserviço B (Porta 8081)**

- **POST /api/sync-data** → Sincroniza os dados
  - **Exemplo de Request**:
    ```http
    POST http://localhost:8081/api/sync-data
    Content-Type: application/json
    ```

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

Usando o Postman ou algum software para fazer as requisições, faça a requisição para consumir a API externa POST - http://localhost:8081/api/sync-data

Feito isso, você estará pronto para requirir as demais funcionalidades da aplicação.
