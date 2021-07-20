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

### Detalhes dos passos efetuados para a refatoração do código:

**1.** Feito o fork do projeto original para a minha conta no GitHub; <br/>
**2.** Criação das branches develop com base na main e a feature/refatoração-código com base na develop; <br/>
**3.** Criação de dois controllers, um somente para os endpoints do cliente e o outro somente para os endpoints de 
cartões; <br/>
**4.** Criação dos packages service, dto, enums e validation; <br/>
**5.** Implementação das regras de negócio com JPA; <br/>
**6.** Ajuste na documentação do swagger; <br/>
**7.** Criação dos testes unitários com o objetivo de testar o código bem e não deixar que um possível build venha a 
"quebrar" em uma eventual esteira DevOps"; <br/>
**8.** Adicionada a collection do postman; <br/>
**9.** Feito a varredura com o SonarLint e corrigido todos os apontamentos para que não ocorra nenhum problema em 
um quality gate do SonarQube de uma esteira DevOps; <br/>
**10.** Incrementada a versão do pom.xml(tagging) para que, em caso de um build ser rodado em uma esteira DevOps, 
não ocorra nenhum problema e o artefato seja armazenado corretamente em uma ferramenta como o Nexus, por exemplo; <br/>
**11.** Alteração do README.md para inclusão dos detalhes das alterações; <br/>
**12.** Feito o pull request para a develop; <br/>
**13.** Feito o pull request para a main; <br/>
**14.** Feito o pedido de pull request para a main do projeto original da Alelo.

### Observações:

Aplicação refatorada no IntelliJ IDEA 2021.1.3 (Ultimate Edition). 

Os comentários dos commits foram feitos em português obedecendo o padrão dos primeiros commits realizados. <br/>

A collection do postman para teste se encontra dentro da pasta resources/postman-collectin/Aplicação consumer - pat.postman_collection

Uma possível melhoria seria se "quebrássemos" a aplicação em dois microsserviços, sendo um para operações com o 
cliente e outro para operações de cartões.
