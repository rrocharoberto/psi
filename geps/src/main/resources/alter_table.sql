-- 1.0.4
ALTER TABLE Registro MODIFY COLUMN TERMOCONSENTIMENTO VARCHAR(300) NULL;
ALTER TABLE Registro MODIFY COLUMN DECLARACAO VARCHAR(300) NULL;

-- 1.0.3

ALTER TABLE REGISTRO ADD COLUMN termoContent bytea NULL;
ALTER TABLE REGISTRO ADD COLUMN declaracaoContent bytea NULL;

ALTER TABLE REGISTRO ADD COLUMN termoOk boolean NOT NULL default false;
ALTER TABLE REGISTRO ADD COLUMN declaracaoOk boolean NOT NULL default false;

ALTER TABLE REGISTRO DROP COLUMN termoContent;
ALTER TABLE REGISTRO DROP COLUMN declaracaoContent;

CREATE TABLE DOCUMENTO (
	numeroProntuario VARCHAR(20) NOT NULL,
	termoContent bytea NULL,
	declaracaoContent bytea NULL,
	CONSTRAINT documento_pk PRIMARY KEY (numeroProntuario)
);
ALTER TABLE DOCUMENTO ADD CONSTRAINT DOCUMENTO_FK FOREIGN KEY (numeroProntuario) REFERENCES REGISTRO (numeroProntuario);

--
alter table registro drop termoContent;
alter table registro drop declaracaoContent;

-- 1.0.2
ALTER TABLE FichaAvaliacao ADD COLUMN data_avaliacao TIMESTAMP;
ALTER TABLE FichaAvaliacao DROP CONSTRAINT ficha_avaliacao_pk;
UPDATE FichaAvaliacao SET data_avaliacao = '2019-06-20';
ALTER TABLE FichaAvaliacao ADD CONSTRAINT ficha_avaliacao_pk PRIMARY KEY (matricula_estagiario, data_avaliacao);

-- 1.0.1
ALTER TABLE usuario ALTER COLUMN password TYPE VARCHAR(100);

-- 1.0.0
CREATE TABLE FichaAvaliacao (
                matricula_estagiario VARCHAR(20) NOT NULL,
                atitude NUMERIC NOT NULL,
                cognitiva NUMERIC NOT NULL,
                habilidade NUMERIC NOT NULL,
                relatorio_cientifico NUMERIC NOT NULL,
                media_geral NUMERIC NOT NULL,
                carga_horaria NUMERIC NOT NULL,
                observacao VARCHAR(255),
                CONSTRAINT ficha_avaliacao_pk PRIMARY KEY (matricula_estagiario)
);


ALTER TABLE FichaAvaliacao ADD CONSTRAINT matricula_estagiario_fk
FOREIGN KEY (matricula_estagiario)
REFERENCES Estagiario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

