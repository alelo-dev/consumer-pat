# Consumer CardPAT
Projeto de teste para seleção de candidatos a vagas de desenvolvimento

## Sobre o projeto
Esse serviço tem o objetivo de gerenciar os dados e realizar transações de clientes que possuam cartões de benefícios, como vale alimentação, refeição e combustivel.

### Requisitos para executar o projeto
- JDK 11
- Maven 3+

## Orientações 
Faça um fork do projeto, clone o mesmo, abra na sua IDE de preferência e execute na sua máquina local. Depois leia as classes(exceto as do pacote config) e verifique se tem algo que você faria diferente. Se encontrar, faça as alterações necessárias. Ao fim, commit e no envie um Pull Request.

### Executando
<p>Ao executar a aplicação você poderá acessa-la em: http://localhost:8080/</p>
<p>Documentação da API: https://consumer-pat.herokuapp.com/swagger-ui/ <br/>
Banco de Dados da API: http://localhost:8080/h2-console/ <br/></p>

 ***Atenção: Não inclua nenhuma lib ou framework***  

## Deploy

Deploy automatizado utilizando workflow do git e heroku, ao realizar um commit ou pull request para a branch develop o deploy é realizado automaticamente e a API estará disponível no endereço https://consumer-pat.herokuapp.com

# REST API

Está disponível dentro dos resources do projeto uma pasta com as collections do postman para importação.

Abaixo a lista detalhada dos endpoints disponíveis

## Atualizar saldo cartão

### Request

`PATCH /card/balance/{cartão}/{valor}`

    curl --location --request PATCH 'https://consumer-pat.herokuapp.com/card/balance/2222222222222222/2000'

### Response

    Status: 200 OK - Saldo atualizado com sucesso
    Status: 400 BAD REQUEST - O valor informado deve ser maior que zero
    Status: 404 NOT FOUND - Cartão inexistente



## Realiza compra

### Request

`PUT /card/buy`

    curl --location --request PUT 'https://consumer-pat.herokuapp.com/card/buy' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "typeEstablishment": 1,
        "establishmentName": "Mercadinho do zé",
        "productDescription": "Compra em mercado",
        "cardNumber": 2222222222222222,
        "value": 170.59
    }'

### Response

    Status: 200 OK - Compra realizada com sucesso
    Status: 400 BAD REQUEST - Saldo inssuficiente
    Status: 400 BAD REQUEST - Cartão nao permitido para uso no estabelecimento informado
    Status: 404 NOT FOUND - Cartão inexistente



## Inserir consumidor

### Request

`POST /consumer`

    curl --location --request POST 'https://consumer-pat.herokuapp.com/consumer' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "name": "Fulano",
        "birthDate": "2000-01-01",
        "documentNumber": 0,
        "address": {
        "city": "Cidade do Fulano",
        "country": "PR",
        "number": 10,
        "portalCode": 85889000,
        "street": "Rua do Fulano"
    },
    "cardList": [
    {
        "cardBalance": 1000,
        "cardNumber": 1111111111111111,
        "typeCard": "DRUGSTORE"
    },
    {
        "cardBalance": 2000,
        "cardNumber": 2222222222222222,
        "typeCard": "FOOD"
    },
    {
        "cardBalance": 3000,
        "cardNumber": 3333333333333333,
        "typeCard": "FUEL"
    }
    ],
    "contactList": [
    {
        "typeContact": "EMAIL",
        "value": "fulano@teste.com"
    },
    {
        "typeContact": "PHONE",
        "value": "44444444444"
    },
    {
        "typeContact": "PHONE",
        "value": "55555555555"
    },
    {
        "typeContact": "PHONE",
        "value": "66666666666"
    }
    ]
    }'

### Response

    Status: 200 OK - Cadastro realizado



## Atualizar consumidor

### Request

`PUT /consumer/{id}`

    curl --location --request PUT 'https://consumer-pat.herokuapp.com/consumer/1' \
    --header 'Content-Type: application/json' \
    --data-raw '{
        "id": 1,
        "active": true,
        "version": 0,
        "name": "Fulano",
        "documentNumber": 0,
        "birthDate": "2000-01-01",
        "address": {
            "id": 2,
            "active": true,
            "version": 0,
            "street": "Rua do Fulano",
            "number": 10,
            "city": "Cidade do Fulano",
            "country": "PR",
            "portalCode": 85889000
        },
    "contactList": [
    {
        "id": 6,
        "active": true,
        "version": 0,
        "typeContact": "PHONE",
        "value": "66666666666",
        "consumer": null
    },
    {
        "id": 7,
        "active": true,
        "version": 0,
        "typeContact": "PHONE",
        "value": "55555555555",
        "consumer": null
    },
    {
        "id": 8,
        "active": true,
        "version": 0,
        "typeContact": "EMAIL",
        "value": "fulano@teste.com",
        "consumer": null
    },
    {
        "id": 9,
        "active": true,
        "version": 0,
        "typeContact": "PHONE",
        "value": "44444444444",
        "consumer": null
    }
    ],
    "cardList": [
    {
        "id": 5,
        "active": true,
        "version": 0,
        "cardNumber": 1111111111111111,
        "cardBalance": 1000.00,
        "typeCard": "DRUGSTORE",
        "consumer": null
    },
    {
        "id": 3,
        "active": true,
        "version": 0,
        "cardNumber": 2222222222222222,
        "cardBalance": 2000.00,
        "typeCard": "FOOD",
        "consumer": null
    },
    {
        "id": 4,
        "active": true,
        "version": 0,
        "cardNumber": 3333333333333333,
        "cardBalance": 3000.00,
        "typeCard": "FUEL",
        "consumer": null
    }
    ]
    }'

### Response

    Status: 200 OK - Cadastro atualizado
    Status: 406 NOT ACCEPTABLE - Não é permitido atualizar o saldo do cartão



## Listar todos consumidores

### Request

`GET /consumer?page=0&size=10`

    curl --location --request GET 'https://consumer-pat.herokuapp.com/consumer?page=0&size=10' \
    --data-raw ''

### Response

    Status: 200 OK - Cadastro removido
    Status: 404 NOT FOUND - Cadastro não encontrado

## Remover consumidor

### Request

`DELETE /consumer/{id}`

    curl --location --request DELETE 'https://consumer-pat.herokuapp.com/consumer/1'

### Response

    Status: 200 OK
    {
    "content": [],
    "pageable": {
        "sort": {
            "sorted": false,
            "unsorted": true,
            "empty": true
        },
        "pageNumber": 0,
        "pageSize": 10,
        "offset": 0,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 0,
    "totalPages": 0,
    "first": true,
    "sort": {
        "sorted": false,
        "unsorted": true,
        "empty": true
    },
    "number": 0,
    "numberOfElements": 0,
    "size": 10,
    "empty": true
}

