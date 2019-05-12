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
                CONSTRAINT estagiario_pk PRIMARY KEY (matricula)
);


ALTER TABLE FichaAvaliacao ADD CONSTRAINT estagiario_avaliacao_fk
FOREIGN KEY (matricula)
REFERENCES Estagiario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

