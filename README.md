# CRUD de clientes
Implementação completa de um CRUD de clientes utilizando Java Spring, H2 e JPA/Hibernate.

## Modelo de domínio

A entidade cliente é composta por:
- id: Long
- name: String
- cpf: String
- income: Double
- birthDate: LocalDate
- children: Integer

## Funcionalidades implementadas
- Busca por id;
- Busca paginada de clientes;
- Inserção de um novo cliente;
- Atualização de um cliente;
- Deleção de um cliente.

## Arquitetura do projeto
![Diagrama crud clientes.jpg](src/main/resources/static/Diagrama%20crud%20clientes.jpg)

## Requisitos para rodar o projeto
- JDK 21+

## Como executar o projeto
1) Verifique se a versão instalada do Java é 21+;
2) Clone este repositório e abra o projeto na IDE de preferência;
3) Aguarde o Maven baixar todas as dependências do projeto;
4) Execute a classe principal do projeto "ClientcrudApplication";
5) Execute as requisições listadas abaixo usando a ferramenta de sua escolha.

## Endpoints
### GET
Busca paginada: http://localhost:8080/clients

Busca paginada com parâmetros: http://localhost:8080/clients?sort=name&size=6

Busca por id: http://localhost:8080/clients/1

### POST

Inserir cliente: http://localhost:8080/clients

Exemplo de payload: 
``` JSON
    {
        "name": "Vincent Valentine",
        "cpf": "32025555501",
        "income": 5400.50,
        "birthDate": "1970-05-18",
        "children": 1
    }
```

### PUT

Atualizar cliente: http://localhost:8080/clients/2

Exemplo de payload:
``` JSON
    {
        "name": "Vincent Valentine",
        "cpf": "32025555501",
        "income": 7500.50,
        "birthDate": "1970-05-18",
        "children": 2
    }
```

### DELETE

Deletar cliente:  http://localhost:8080/clients/2