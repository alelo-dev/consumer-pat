INSERT INTO tb_country (name) values ('Brasil'), ('Uruguai'), ('Inglaterra');
INSERT INTO tb_state (name, country_id) values ('São Paulo',1), ('Parana',1), ('Minas Gerais',1);
INSERT INTO tb_city (name, state_id) VALUES ('São Paulo', 1), ('Campinas', 1), ('São José dos Campos', 1), ('Curitiba', 3), ('Três Rios', 2);

--INSERT INTO tb_restaurante (endereco_cep, endereco_logradouro, endereco_numero, endereco_complemento, endereco_bairro, endereco_cidade_id, nome, taxa_frete, cozinha_id, data_cadastro, data_atualizacao) VALUES ('02011300', 'Rua voluntarios', '1030', 'ap 61', 'Santana', 1 ,'Thai Food', 10, 1, utc_timestamp, utc_timestamp), ('02012130', 'Rua aviador', '111', '', 'Santana', 1 ,'Thai Delivery', 9.5, 2, utc_timestamp, utc_timestamp), ('05127030', 'Rua Luís', '97', '', 'Pq. São Domingos', 1 ,'Tuk Tuk Indian Food', 15, 3, utc_timestamp, utc_timestamp);
--
--INSERT INTO tb_forma_pagamento (descricao) VALUES ('Cartão de Crédito'), ('Cartão de Débito'), ('Dinheiro');
--
--INSERT INTO tb_restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) VALUES (1, 1), (1, 2), (1, 3), (2, 1), (2,2), (3,1), (3,2), (3,3);
--
--INSERT INTO tb_produto (ativo, descricao, nome, preco, restaurante_id) VALUES (true, 'Pizza quatro queijos', 'Quatro queijos', 59.90, 3), (true, 'Pizza dois queijos', 'Dois queijos', 59.90, 1), (true, 'Comida apimentada da porra', 'Sopa de pimenta', 59.90, 2);
--
--INSERT INTO tb_grupo (nome) VALUES ('Administrador'), ('Gerente'), ('Diretor');
--
--INSERT INTO tb_permissao (nome, descricao) VALUES ('admin', 'Gerenciamento de Usuários'), ('financeiro', 'Time do financeiro'), ('rh', 'Time do RH'), ('compras', 'Time de compras');
--
--INSERT INTO tb_grupo_permissao (grupo_id, permissao_id) VALUES (1,1), (2,2), (2,4), (3,2), (3,3), (3,4);
--
--INSERT INTO tb_usuario (nome, email, senha, data_cadastro) VALUES ('Rodrigo', 'rodrigo@gmail.com', 'abc123', utc_timestamp), ('Ana', 'ana@gmail.com', 'abc456',utc_timestamp), ('Maya', 'maya@gmail.com', 'abc789',utc_timestamp);
--
--INSERT INTO tb_usuario_grupo (usuario_id, grupo_id) VALUES (1, 1), (2,3), (3,2);
