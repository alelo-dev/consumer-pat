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

## Alterações no projeto

O conceito usado foi **Responsabilidade por Camada**. Desta forma, a implementação será baseada em sua finalidade técnica. Por exemplo, a camada de repositório será responsável por permitir a comunicação com o banco de dados.

- **Model** - Classes/Entidades de negócios
- **Enums** - Enumeradores de negócios
- **DTO** - Objeto de Transferência de Dados
- **Validator** - Validador de negócio
- **Repository** - Comunicação com banco de dados
- **Service** - Serviços para atender às especificações
- **Controller** - Interceptador de requisição
- **Config** - Componente de configuração
- **Util** - Utilitário para negócios

### Testes integrados

Para este projeto, os testes integrados via Postman disponibilizados através de um arquivo que contém requisições, conteúdo e outros. Os testes estão disponíveis em uma pasta chamada collections_integrated_tests no projeto.