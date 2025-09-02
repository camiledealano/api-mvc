# API de Gerenciamento de Tarefas

Este projeto é uma API RESTful desenvolvida com Spring Boot para gerenciar tarefas. Ele permite criar, listar, atualizar e deletar tarefas, cada uma com título, descrição, prioridade, status e data limite.

## Tecnologias Utilizadas

*   **Java 17**: Linguagem de programação.
*   **Spring Boot 3.5.5**: Framework para construção de aplicações Java.
*   **Spring Web**: Para construção de APIs RESTful.
*   **Spring Data JPA**: Para persistência de dados com o Hibernate.
*   **H2 Database**: Banco de dados em memória para desenvolvimento e testes.
*   **Lombok**: Biblioteca para reduzir código boilerplate.
*   **Maven**: Ferramenta de automação de build.

## Funcionalidades

*   **Criação de Tarefas**: Adiciona novas tarefas ao sistema.
*   **Listagem de Tarefas**: Recupera todas as tarefas ou uma tarefa específica por ID.
*   **Atualização de Tarefas**: Modifica os detalhes de uma tarefa existente.
*   **Exclusão de Tarefas**: Remove tarefas do sistema.

## Estrutura do Projeto

O projeto segue a estrutura padrão de uma aplicação Spring Boot:

```
API-MVC/
├── src/
│   ├── main/
│     ├── java/
│     │   └── com/
│     │       └── example/
│     │           └── API/
│     │               └── MVC/
│     │                   ├── ApiMvcApplication.java    # Classe principal da aplicação
│     │                   ├── controller/             # Controladores REST
│     │                   │   └── TarefaController.java
│     │                   ├── dto/                    # Data Transfer Objects (DTOs)
│     │                   │   ├── TarefaDTO.java
│     │                   │   └── TarefaRequestDTO.java
│     │                   ├── mapper/                 # Mapeadores entre DTOs e Entidades
│     │                   │   └── TarefaMapper.java
│     │                   ├── model/                  # Modelos de dados e enums
│     │                   │   ├── Tarefa.java
│     │                   │   └── enums/
│     │                   │       ├── Prioridade.java
│     │                   │       └── Status.java
│     │                   └── repository/             # Repositórios JPA
│     │                       └── TarefaRepository.java
│     └── resources/
│          └── application.properties  # Configurações da aplicação
│  
└── README.md
```

## Como Executar o Projeto

### Pré-requisitos

Certifique-se de ter o Java Development Kit (JDK) 17 ou superior instalado em sua máquina.

### Configuração

Não é necessária nenhuma configuração adicional para o banco de dados H2 em memória, pois ele é configurado automaticamente pelo Spring Boot.

### Execução

1.  **Clone o repositório (se aplicável):**

    ```bash
    git clone <URL_DO_REPOSITORIO>
    cd API-MVC
    ```

2.  **Compile e execute a aplicação usando Maven:**

    ```bash
    ./mvnw spring-boot:run
    ```

    A aplicação será iniciada na porta padrão 8080.

## Endpoints da API

A API expõe os seguintes endpoints para gerenciamento de tarefas:

### `GET /api/tarefas`

Lista todas as tarefas cadastradas, retornando objetos `TarefaDTO`.

**Exemplo de Resposta:**

```json
[
  {
    "id": 1,
    "titulo": "Comprar Leite",
    "descricao": "Ir ao supermercado e comprar leite integral",
    "prioridade": "ALTA",
    "status": "PENDENTE",
    "dataLimite": "2025-08-30"
  },
  {
    "id": 2,
    "titulo": "Pagar Contas",
    "descricao": "Pagar contas de água e luz",
    "prioridade": "MEDIA",
    "status": "CONCLUIDA",
    "dataLimite": "2025-08-25"
  }
]
```

### `GET /api/tarefas/{id}`

Busca uma tarefa específica pelo seu ID, retornando um objeto `TarefaDTO`.

**Parâmetros de Path:**

*   `id` (Long): O ID da tarefa.

**Exemplo de Resposta (Sucesso - 200 OK):**

```json
{
  "id": 1,
  "titulo": "Comprar Leite",
  "descricao": "Ir ao supermercado e comprar leite integral",
  "prioridade": "ALTA",
  "status": "PENDENTE",
  "dataLimite": "2025-08-30"
}
```

**Exemplo de Resposta (Não Encontrado - 404 Not Found):**

```
(Corpo vazio)
```

### `POST /api/tarefas`

Cria uma nova tarefa, aceitando um objeto `TarefaRequestDTO` e retornando um `TarefaDTO`.

**Corpo da Requisição (JSON - TarefaRequestDTO):**

```json
{
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar conceitos de Spring Boot e JPA",
  "prioridade": "ALTA",
  "status": "PENDENTE",
  "dataLimite": "2025-09-15"
}
```

**Exemplo de Resposta (Sucesso - 200 OK - TarefaDTO):**

```json
{
  "id": 3,
  "titulo": "Estudar Spring Boot",
  "descricao": "Revisar conceitos de Spring Boot e JPA",
  "prioridade": "ALTA",
  "status": "PENDENTE",
  "dataLimite": "2025-09-15"
}
```

### `PUT /api/tarefas/{id}`

Atualiza uma tarefa existente pelo seu ID, aceitando um objeto `TarefaRequestDTO` e retornando um `TarefaDTO`.

**Parâmetros de Path:**

*   `id` (Long): O ID da tarefa a ser atualizada.

**Corpo da Requisição (JSON - TarefaRequestDTO):**

```json
{
  "titulo": "Comprar Leite Desnatado",
  "descricao": "Ir ao supermercado e comprar leite desnatado",
  "prioridade": "MEDIA",
  "status": "CONCLUIDA",
  "dataLimite": "2025-08-30"
}
```

**Exemplo de Resposta (Sucesso - 200 OK - TarefaDTO):**

```json
{
  "id": 1,
  "titulo": "Comprar Leite Desnatado",
  "descricao": "Ir ao supermercado e comprar leite desnatado",
  "prioridade": "MEDIA",
  "status": "CONCLUIDA",
  "dataLimite": "2025-08-30"
}
```

**Exemplo de Resposta (Não Encontrado - 404 Not Found):**

```
(Corpo vazio)
```

### `DELETE /api/tarefas/{id}`

Deleta uma tarefa pelo seu ID.

**Parâmetros de Path:**

*   `id` (Long): O ID da tarefa a ser deletada.

**Exemplo de Resposta (Sucesso - 204 No Content):**

```
(Corpo vazio)
```

**Exemplo de Resposta (Não Encontrado - 404 Not Found):**

```
(Corpo vazio)
```

---
