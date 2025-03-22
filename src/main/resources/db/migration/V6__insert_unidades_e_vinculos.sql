-- Migration para inserir unidades governamentais e vincular com endereços

-- Inserção de Unidades (Secretarias e outros órgãos governamentais)
INSERT INTO unidade (unid_nome, unid_sigla) VALUES
('Secretaria de Estado de Educação', 'SEDUC'),
('Secretaria de Estado de Saúde', 'SES'),
('Secretaria de Estado de Fazenda', 'SEFAZ'),
('Secretaria de Estado de Segurança Pública', 'SESP'),
('Secretaria de Estado de Planejamento e Gestão', 'SEPLAG'),
('Secretaria de Estado de Infraestrutura', 'SINFRA'),
('Secretaria de Estado de Meio Ambiente', 'SEMA'),
('Secretaria de Estado de Agricultura Familiar', 'SEAF');

-- Vinculação de Unidades com Endereços
-- Cada unidade terá endereço principal em Cuiabá e algumas terão filiais em outras cidades

-- Endereços em Cuiabá (Sede das unidades)
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(1, 1),  -- SEDUC - Av. Historiador Rubens de Mendonça
(2, 2),  -- SES - Rua Barão de Melgaço
(3, 3),  -- SEFAZ - Rua Joaquim Murtinho
(4, 4),  -- SESP - Av. Fernando Corrêa da Costa
(5, 1),  -- SEPLAG - Av. Historiador Rubens de Mendonça (mesmo prédio da SEDUC)
(6, 2),  -- SINFRA - Rua Barão de Melgaço (mesmo prédio da SES)
(7, 3),  -- SEMA - Rua Joaquim Murtinho (mesmo prédio da SEFAZ)
(8, 4);  -- SEAF - Av. Fernando Corrêa da Costa (mesmo prédio da SESP)


-- Filiais em Rondonópolis
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(1, 8),  -- SEDUC - Av. Lions Internacional
(2, 9),  -- SES - Rua Fernando Corrêa da Costa
(3, 10), -- SEFAZ - Rua Dom Pedro II
(4, 8);  -- SESP - Av. Lions Internacional (mesmo prédio da SEDUC filial)

-- Filiais em Várzea Grande
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(2, 5),  -- SES - Av. Filinto Müller
(4, 6);  -- SESP - Rua Presidente Arthur Bernardes


-- Filiais em Sinop
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(1, 11), -- SEDUC - Av. das Itaúbas
(3, 12), -- SEFAZ - Av. dos Ingás
(7, 13); -- SEMA - Rua das Primaveras

-- Filiais em Tangará da Serra
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(2, 14), -- SES - Av. Brasil
(6, 15); -- SINFRA - Rua José Corsino

-- Filiais em outras cidades
INSERT INTO unidade_endereco (unid_id, end_id) VALUES
(3, 16), -- SEFAZ - Cáceres - Rua Coronel José Dulce
(4, 17), -- SESP - Cáceres - Av. Getúlio Vargas
(1, 18), -- SEDUC - Sorriso - Av. Natalino João Brescansin
(2, 19), -- SES - Sorriso - Rua São José
(5, 20); -- SEPLAG - Lucas do Rio Verde - Av. Paraná