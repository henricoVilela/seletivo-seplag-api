-- Criação da tabela pessoa
CREATE TABLE pessoa (
    pes_id SERIAL PRIMARY KEY,
    pes_nome VARCHAR(200) NOT NULL,
    pes_data_nascimento DATE,
    pes_sexo VARCHAR(1),
    pes_mae VARCHAR(200),
    pes_pai VARCHAR(200)
);

-- Criação da tabela foto_pessoa
CREATE TABLE foto_pessoa (
    fp_id SERIAL PRIMARY KEY,
    pes_id INT NOT NULL,
    fp_data TIMESTAMP,
    fp_bucket VARCHAR(50),
    fp_hash VARCHAR(50),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);

-- Criação da tabela cidade
CREATE TABLE cidade (
    cid_id SERIAL PRIMARY KEY,
    cid_nome VARCHAR(200) NOT NULL,
    cid_uf VARCHAR(2)
);

-- Criação da tabela endereco
CREATE TABLE endereco (
    end_id SERIAL PRIMARY KEY,
    end_tipo_logradouro VARCHAR(10),
    end_logradouro VARCHAR(200),
    end_numero INT,
    end_bairro VARCHAR(100),
    cid_id INT,
    FOREIGN KEY (cid_id) REFERENCES cidade(cid_id)
);

-- Criação da tabela pessoa_endereco
CREATE TABLE pessoa_endereco (
    pes_id INT NOT NULL,
    end_id INT NOT NULL,
    PRIMARY KEY (pes_id, end_id),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id),
    FOREIGN KEY (end_id) REFERENCES endereco(end_id)
);

-- Criação da tabela unidade
CREATE TABLE unidade (
    unid_id SERIAL PRIMARY KEY,
    unid_nome VARCHAR(200) NOT NULL,
    unid_sigla VARCHAR(20)
);

-- Criação da tabela unidade_endereco
CREATE TABLE unidade_endereco (
    unid_id INT NOT NULL,
    end_id INT NOT NULL,
    PRIMARY KEY (unid_id, end_id),
    FOREIGN KEY (unid_id) REFERENCES unidade(unid_id),
    FOREIGN KEY (end_id) REFERENCES endereco(end_id)
);

-- Criação da tabela servidor_temporario
CREATE TABLE servidor_temporario (
    pes_id INT PRIMARY KEY,
    st_data_admissao DATE,
    st_data_demissao DATE,
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);

-- Criação da tabela servidor_efetivo
CREATE TABLE servidor_efetivo (
    pes_id INT PRIMARY KEY,
    se_matricula VARCHAR(20),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id)
);

-- Criação da tabela lotacao
CREATE TABLE lotacao (
    lot_id SERIAL PRIMARY KEY,
    pes_id INT NOT NULL,
    unid_id INT NOT NULL,
    lot_data_lotacao DATE,
    lot_data_remocao DATE,
    lot_portaria VARCHAR(100),
    FOREIGN KEY (pes_id) REFERENCES pessoa(pes_id),
    FOREIGN KEY (unid_id) REFERENCES unidade(unid_id)
);