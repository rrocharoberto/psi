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
                carga_horario NUMERIC NOT NULL,
                observacao VARCHAR(255),
                CONSTRAINT ficha_avaliacao_pk PRIMARY KEY (matricula_estagiario)
);


ALTER TABLE FichaAvaliacao ADD CONSTRAINT matricula_estagiario_fk
FOREIGN KEY (matricula_estagiario)
REFERENCES Estagiario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

