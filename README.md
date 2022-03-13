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
<p>Documentação da API: http://localhost:8080/swagger-ui/ <br/>
Banco de Dados da API: http://localhost:8080/h2-console/ <br/></p>

 ***Atenção: Não inclua nenhuma lib ou framework***  

### Versao com melhorias.
 - Realizar build da app para gerar classes do mapStruct
 - 
 
### Observações gerais
 - Creio que a app para realização de teste esteja concisa, porém para ambiente real acredito que a divisão de sua resposabilidade em mais de um microservico seria palpavel;
 
### endpoints/requests
 - buscar todos consumers:

~~~cURL
curl --location --request GET 'localhost:8080/v1/consumer/consumerList'
~~~
 - Criar consumers:
 
~~~cURL
curl --location --request POST 'localhost:8080/v1/consumer/createConsumer' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Fulano da Rocha",
    "documentNumber": 2423,
    "birthDate": "1980-10-20",
    "contact": {
        "mobilePhoneNumber": "85999999999",
        "residencePhoneNumber": "85999999999",
        "phoneNumber": "85999999999",
        "email": "fulano@email.com"
    },
    "adress": {
        "street": "Rua A",
        "number": 234,
        "city": "Caucaia",
        "country": "Brasil",
        "portalCode": 55
    },
    "cards": [
        {
            "cardNumber": 1,
            "balance": 10.00,
            "typeCard": "FUEL"
        },
        {
            "cardNumber": 2,
            "balance": 10.00,
            "typeCard": "FOOD"
        },
        {
            "cardNumber": 3,
            "balance": 10.00,
            "typeCard": "DRUGSTORE"
        }
    ]
}'
~~~
 - Realizar compra

~~~cURL
curl --location --request POST 'localhost:8080/v1/consumer/buy' \
--header 'Content-Type: application/json' \
--data-raw '{
    "establishment": {
        "id" : 1,
        "authorizedCard": "DRUGSTORE",
        "establishmentName": "Posto 3 corações"
    },
    "cardValueTransaction": {
        "cardNumber": 3,
        "value": 1
    },
    "productDescription": "Combustivel e seus derivados"
}'
~~~
 