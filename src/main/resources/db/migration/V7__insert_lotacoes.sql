-- Migration para inserir registros de lotação das pessoas nas unidades

-- Lotação dos servidores efetivos
INSERT INTO lotacao (pes_id, unid_id, lot_data_lotacao, lot_data_remocao, lot_portaria) VALUES
-- Maria Silva Oliveira - Servidora efetiva na SEDUC
(1, 1, '2015-02-10', NULL, 'Portaria nº 023/2015/SEDUC'),

-- Ana Carolina Ferreira - Servidora efetiva na SES
(3, 2, '2016-05-15', NULL, 'Portaria nº 087/2016/SES'),

-- Juliana Almeida Souza - Servidora efetiva na SEFAZ
(5, 3, '2018-03-01', NULL, 'Portaria nº 042/2018/SEFAZ'),

-- Camila Rodrigues Lima - Servidora efetiva na SESP
(7, 4, '2017-07-20', NULL, 'Portaria nº 114/2017/SESP'),

-- Amanda Cardoso Vieira - Servidora efetiva na SINFRA
(11, 6, '2020-03-10', NULL, 'Portaria nº 031/2020/SINFRA'),

-- Carolina Barbosa Silva - Servidora efetiva na SEMA com histórico de remoção
(13, 7, '2018-08-15', '2022-02-28', 'Portaria nº 093/2018/SEMA'),
(13, 3, '2022-03-01', NULL, 'Portaria nº 027/2022/SEFAZ'), -- Removida para SEFAZ

-- Bianca Correia Lima - Servidora efetiva na SEAF com histórico de remoção
(15, 8, '2019-06-05', '2021-09-30', 'Portaria nº 076/2019/SEAF'),
(15, 5, '2021-10-01', NULL, 'Portaria nº 112/2021/SEPLAG'); -- Removida para SEPLAG

-- Lotação dos servidores temporários
INSERT INTO lotacao (pes_id, unid_id, lot_data_lotacao, lot_data_remocao, lot_portaria) VALUES
-- Rafael Martins Pereira - Servidor temporário com lotação atual na ALMT e histórico
(8, 2, '2023-05-20', '2023-11-30', 'Portaria nº 047/2023/SES'), -- Inicialmente na SES
(8, 3, '2023-12-01', NULL, 'Portaria nº 138/2023/SEFAZ'), -- Removido para SEFAZ

-- Bruno Alves Ribeiro - Servidor temporário na SEDUC filial Rondonópolis
(10, 1, '2023-06-01', NULL, 'Portaria nº 062/2023/SEDUC'),

-- Thiago Moreira Santos - Servidor temporário na SES filial Várzea Grande
(12, 2, '2023-04-15', NULL, 'Portaria nº 037/2023/SES'),

-- Gustavo Teixeira Rocha - Servidor temporário na SEFAZ com histórico
(14, 3, '2023-07-01', '2023-10-15', 'Portaria nº 075/2023/SEFAZ'), -- Inicialmente na sede SEFAZ
(14, 3, '2023-10-16', NULL, 'Portaria nº 118/2023/SEFAZ'); -- Transferido para filial Sinop (mesma unidade, mudança não registrada na tabela lotacao)