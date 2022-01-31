#Intervenções realizadas no código em 29/01/2022
Atualização da forma de chamada de requisições para a mais atual suportada pela versão do SpringBoot (@GetMapping, @PostMapping, etc)
Inclusão de camada de serviço
Inclusão dois estereotípos presentes no Spring
Remoção de imports não utilizados nas classes de projeto.
Tratamento nas entidades para mudar o tipo de campo para BigDecimal por causa da perda de precisão, fato conhecimento quando se trata de dados finaceiros.
Criação de um Enum para separar os tipos de cartões
Criação de tabela específica para cartões
Inclusão de da propriedade do Enum na tabela de cartões.
Criação de um converter para fazer a conversão dos tipos do Enum para valor no banco de dados, evitando alteração de ordem, para não consumir pelo Ordinal.
Inclusão do id do responsável na tabela de cartões
Remoção dos cartões e valores chapados na entidade consumer para apontar para entidade nova criada.
Atualização do Balance do Card movido para o CardService,
Simplificação do método de atualização para remover os IFs aninhados desnecessários para forma de consulta, mesmo sem a alteração da forma, já poderia ser simplificao, pois a busca é sempre pelo cardNumber, não importando o tipo na busca então os Ifs ali não fazem sentido, eu solicitaria revisão do DEVE.
A responsabilidade de atualização de saldo do cartão foi removido do Endpoint de consumer e passado para o novo Endpoint de CArtões. .
Inclusão de DTOs de retorno para reduzir a quantidade de informações retornada e retornar apenas o necessário.
Inclusão de retorno nos métodos Void que faziam sentido ter algum retorno nas chamadas.
Inclusão de Logs em caso de falhas nas requisições realizadas (Em outros projetos a dependender do contexto, principalmente legados, em times que trabalhei chegamos a colocar os logs nas entradas e saídas dos métodos, um projeto de micro serviços que nasceu meio desestruturado e cresceu bastante, foi necessário para facilitar encontrar fluxos e bugs no sistema, apesar de utilizar-mos Kibana, elasticSearch, new Relic e outras ferramentas de monitoria)
Seraração das responsabilidades presentes da API
Criação de um novo Endpoint para Cartão
Criação de um novo Endpoint para processamento de transações.
Ajuste do Endpoint principal de customer para remover responsábilidades não associadas ao domínio de Customer.
Dependendo do contexto, outra sugestão seria quebrar em mais APIS
A parte de controle de criação de cartões, cadastros de consumers e transações
para deixar em contextos diferentes, containers.
Incluída validação para evitar compras de produtos acima do valor disponível no balance.
Dependendo do contexto eu iria sugerir colocar os estabelecimentos em um Redis ou MoboDB para os estabelcimentos e poder recuperar os tipos de cartão que são aceitos naquele estabelecimento, se for o caso.
O Método Buy que esta sendo recebido no GET , foi passado para um POST já que não é recuperação de valores para ficar mais condizente com o padrão.
Foi Criado um DTO para receber os dados da compra.
Foi incluida entidade para estabelecimento, Service, e Repository
Foi incluido novo Endpoint para o estabelecimento.
Redução dos parâmetros utilizadas na requisição de compra. 
O Estabelecimento e recuperado a partir do ID, e o tipo disponível para transação é verificado para evitar uso indevido não corresponde ao disponível para o Estabelecimento, evitando inconsistência.
Inclusão da classe ShoppingService para tratar das operações e aplicação de regras de negócio ligados a compras.
Na entidade que registra o extrato e nas datas eu colocaria salvando a data e hora com base no LocalDateTime utilizando GMT pensando em internacionalização e utilizando um recurso mais atual do Java.
Criação de Mappers para entidades da API para conversão Entity To DTO e DTO to Entity Para alguns tratamentos como mapear de DTO para Entity e de Entity eu utilizaria alguma framework como o ModelMapper, fiz manual, por causa da restrição de não utilizar frameworks externos.
Incluiria descrições mais detalhadas nas chamadas dos métodos em anotações do Swagger, coloquei isso na entidade do ControllerCosummer.
Não consegui ver exatamente a necessidade dos construtores na entidade Extract, ou pode trazer alguma inconsistência por não ter uma forma de validar o estabelecimento.
Bom de qualquer forma, nesse cara utilize o Lombok para fazer isso tanto para nenhum parâmetro no construtor como para todos os construtores.
Coloquei a opção de usar o padrão Builder para poder chamar na classe ShoppingService que foi adicionada para tratar as compras.
Não existe necessidade de colocar a anotação @ReponseBody nas chamadas, pois a anotação RestController já suporta isso;
Inclusão de Exception para exceções de negócio, poderiam ter sido incluídas mais para entidades não encontradas, saldo insufience, entre outras regras. (Precisaria entender a necessidae para isso, então deixei o básico)
Inclusão de Exception para erros de requisição.
Inclusão de um hnadler para capturar erros e retornar algumas informação que não exponha aAPI em caso de falha.
Remoção da query nativa para deixar o SpringData se responsabilizar pela query em ConsumerRepository
Remoção das demais queryes redudantes para se obter o Consumer a partir do CardNumber, dado que as buscas são apenas pelo CadNumber sempre.
Inclusão de páginação para listagem de usuários
Inclusão de páginação para listagem de extratos.
Inclusão de um EndPoint Paginado para recuperação de extratos.
Atualização do campo de Telefone para String para suportar números com DDD e não darem erros como -> Numeric value (83985656848) out of range of int (-2147483648 - 2147483647)
Passar sonarlint no código para validar dados errados.
As variaveis locais foram definidas com var. 
Incluída validação para verificar se já não existe um número de cartão cadastrado.
Inclusão de recuperação de lista de consumers por DocumentNumber. 

# Sugestão de melhorias que poderiam ser feitas no domingo. Só consegui olhar o código na sexta bem rápido e sábado depois das 10:00.
Criação de um novo Endpoint para Contatos  (Facilita o carregamento sobre demanda, uma vez que nem sempre é necessário trazer todos os dados para tela) <- A depender do contexto, mas preferi separar aqui, mas seria preciso uma análise de Usabilidade para ver qual a real necessidade em tela
Criação de um novo Endpoint para Endereços (Facilita o carregamento sobre demanda, uma vez que nem sempre é necessário trazer todos os dados para tela ) (Facilita o carregamento sobre demanda, quando não for necessário carregar os contatos) <- A depender do contexto, mas preferi separar aqui, mas seria preciso uma análise de Usabilidade para ver qual a real necessidade em tela
Por último incluir segurança para realização dos requests as APIS, terminei vendo os demais itens e não foi possível adicionar esse item.
Criação de uma entidade de Contatos  (Não adicionado, por causa do tempo, foquei nas outras coisas)
Criação de uma entidade de endereços (Não adicionado, por causa do tempo, foquei nas outras coisas, sem falar que eu acho que seria necessário ver a necessidade de separar realmente, evitando outra busca do banco, a depender da quantidade de requisições)
Melhorar os tipos de exceção capturados do RequestHandlerException
Incluir internacionalização de mensagens no RequestHandlerException
Incluir os testes unitários para cobertura de pelo menos X% de cobertura dos testes, estabelecer uma política com o time no caso de projetos legados.
Incluir testes regressisvos.
Completar a cobertura de testes.
Incluir configuração para execução do Sonar.
Dockerizar a API.
Rever as chamadas dos endpoints para as operações básicas.
Incluir uma classe abstrata para realizar generilização das ações mais comuns de CRUD nas entidades de Service.
Para a base de dados utilizaria o Liquibase ou Flyway para vesionamento também, não coloquei algumas coisas por causa das restrições informadas. 
Incluiria de validators para os tipos de dados, no caso do cartão se existe um tamanho mínimo ou padrão. 
Pensei em incluir cache para os usuários, visto que são apenas 500 usuários, não seria algo que impactaria tanto na performance, a depender do ambiente. 

# Melhorias incluídas no domingo
Inclusão dos testes de API usando o MockMvc mockMvc.
Inclusão de testes unitários mocados para CardService. 
Otimizão do Mapper . 