INSERT INTO TB_CARD_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (1, 'CARTÃO ALIMENTAÇÃO', 'CARTÃO ALIMENTACAO', NOW());
INSERT INTO TB_CARD_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (2, 'CARTÃO FARMÁCIA', 'FARMÁCIA', NOW());
INSERT INTO TB_CARD_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (3, 'CARTÃO COMBUSTÍVEL', 'POSTO DE COMBUSTÍVEL', NOW());

INSERT INTO TB_ESTABLISHMENT_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (1, 'RESTAURANTE', 'LOJA ALIMENTACAO', NOW());
INSERT INTO TB_ESTABLISHMENT_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (2, 'FARMÁCIA POPULAR', 'FARMÁCIA', NOW());
INSERT INTO TB_ESTABLISHMENT_TYPE (ID, NAME, DESC, CREATED_AT) VALUES (3, 'POSTO COMBUSTÍVEL', 'POSTO DE COMBUSTÍVEL', NOW());

INSERT INTO TB_ESTABLISHMENT (ID, NAME, DESC, ESTABLISHMENTTYPE_ID, CREATED_AT) VALUES (1, 'RESTAURANTE', 'LOJA ALIMENTACAO', 1, NOW());




INSERT INTO TB_PARAMETER (ID, NAME, VALUE, CREATED_AT) VALUES (1, 'TAXA_CARTAO_COMBUSTIVEL', '35', NOW());
INSERT INTO TB_PARAMETER (ID, NAME, VALUE, CREATED_AT) VALUES (2, 'TAXA_CARTAO_ALIMENTACAO', '10', NOW());


