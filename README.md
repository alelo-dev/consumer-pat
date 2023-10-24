# Desafio Técnico

O objetivo deste desafio é avaliar suas habilidades em realizar um 'code review' e melhorar a qualidade do código de 
uma API desenvolvida em Spring Boot, utilizada para gerenciar os consumidores e as transações de cartão de benefícios dos consumidores. 

Suas sugestões e implementações serão fundamentais para garantir que a API seja escalável, performática, de fácil de manutenção, 
resiliente e que funcione de forma eficiente. Isso é crucial para o sucesso do projeto e para garantir que os usuários tenham uma experiência satisfatória. 

Como desenvolvedor, você é responsável por garantir que o código seja limpo, legível e siga as boas práticas de programação. 
Então, esteja preparado para mostrar suas habilidades e sua capacidade de tomar decisões críticas para melhorar o código existente.

**Atenção: Não inclua nenhuma nova dependência (biblioteca ou pacote) ao projeto. Caso julgue necessário, os contratos da API podem ser alterados.**

## Requisitos para executar o projeto

* JDK `11` ou `17`

## Orientações 

* Faça um fork do projeto;
* Clone o repositório;
* Abra em sua IDE de preferência (IntelliJ, VS Code, Eclipse, NetBeans, etc.); 
* Leia as classes (exceto as do pacote config) e verifique se existe algo que você faria diferente (Code Review); 
* Se encontrar, faça as alterações necessárias;
* Ao fim, realize o **commit** e nos envie um **Pull Request**.

Observação: caso não possua acesso a uma IDE e necessite realizar as alterações, o GitHub possui um editor web, 
basta clicar [aqui](https://github.dev/alelo-dev/consumer-pat) para acessar.

## Executando
Ambiente Linux/macOS:
```bash
./gradlew bootRun
```
Ambiente Windows:
```cmd
gradlew bootRun
```

Docker:

1 - Acessa a pasta do projeto

2 -
 
```bash 
./gradlew clean build
```

3 -

```bash 
./docker compose up
```


## Documentação da API (Swagger)

* URL: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

## Acesso ao Banco de Dados em memória (H2)

* URL: [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/)
* JDBC URL: `jdbc:h2:mem:consumer-pat`
* User Name: `sa`
* Password: `sa`
