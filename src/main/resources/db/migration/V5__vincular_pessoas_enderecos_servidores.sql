-- Migration para vincular pessoas a endereços e classificá-las como servidores efetivos ou temporários

-- Vincular pessoas a endereços
-- Cada pessoa terá um endereço principal
INSERT INTO pessoa_endereco (pes_id, end_id) VALUES
(1, 1),   -- Maria Silva Oliveira - Endereço em Cuiabá (Av. Historiador Rubens de Mendonça)
(2, 5),   -- João Pedro Santos - Endereço em Várzea Grande (Av. Filinto Müller)
(3, 8),   -- Ana Carolina Ferreira - Endereço em Rondonópolis (Av. Lions Internacional)
(4, 11),  -- Lucas Mendes Costa - Endereço em Sinop (Av. das Itaúbas)
(5, 2),   -- Juliana Almeida Souza - Endereço em Cuiabá (Rua Barão de Melgaço)
(6, 6),   -- Pedro Henrique Gomes - Endereço em Várzea Grande (Rua Presidente Arthur Bernardes)
(7, 9),   -- Camila Rodrigues Lima - Endereço em Rondonópolis (Rua Fernando Corrêa da Costa)
(8, 14),  -- Rafael Martins Pereira - Endereço em Tangará da Serra (Av. Brasil)
(9, 12),  -- Fernanda Costa Oliveira - Endereço em Sinop (Av. dos Ingás)
(10, 16), -- Bruno Alves Ribeiro - Endereço em Cáceres (Rua Coronel José Dulce)
(11, 3),  -- Amanda Cardoso Vieira - Endereço em Cuiabá (Rua Joaquim Murtinho)
(12, 18), -- Thiago Moreira Santos - Endereço em Sorriso (Av. Natalino João Brescansin)
(13, 4),  -- Carolina Barbosa Silva - Endereço em Cuiabá (Av. Fernando Corrêa da Costa)
(14, 19), -- Gustavo Teixeira Rocha - Endereço em Sorriso (Rua São José)
(15, 20); -- Bianca Correia Lima - Endereço em Lucas do Rio Verde (Av. Paraná)

-- Algumas pessoas com endereço secundário
INSERT INTO pessoa_endereco (pes_id, end_id) VALUES
(1, 7),   -- Maria Silva Oliveira - Endereço secundário em Várzea Grande
(3, 15),  -- Ana Carolina Ferreira - Endereço secundário em Tangará da Serra
(5, 10),  -- Juliana Almeida Souza - Endereço secundário em Rondonópolis
(8, 17);  -- Rafael Martins Pereira - Endereço secundário em Cáceres

-- Cadastro de servidores efetivos (8 pessoas)
INSERT INTO servidor_efetivo (pes_id, se_matricula) VALUES
(1, 'MAT0001'),  -- Maria Silva Oliveira
(3, 'MAT0002'),  -- Ana Carolina Ferreira
(5, 'MAT0003'),  -- Juliana Almeida Souza
(7, 'MAT0004'),  -- Camila Rodrigues Lima
(9, 'MAT0005'),  -- Fernanda Costa Oliveira
(11, 'MAT0006'), -- Amanda Cardoso Vieira
(13, 'MAT0007'), -- Carolina Barbosa Silva
(15, 'MAT0008'); -- Bianca Correia Lima

-- Cadastro de servidores temporários (7 pessoas)
INSERT INTO servidor_temporario (pes_id, st_data_admissao, st_data_demissao) VALUES
(2, '2023-01-15', '2024-01-14'),    -- João Pedro Santos
(4, '2023-03-01', '2024-02-28'),    -- Lucas Mendes Costa
(6, '2023-02-10', '2024-08-09'),    -- Pedro Henrique Gomes
(8, '2023-05-20', '2024-05-19'),    -- Rafael Martins Pereira
(10, '2023-06-01', '2024-11-30'),   -- Bruno Alves Ribeiro
(12, '2023-04-15', '2024-10-14'),   -- Thiago Moreira Santos
(14, '2023-07-01', '2024-12-31');   -- Gustavo Teixeira Rocha