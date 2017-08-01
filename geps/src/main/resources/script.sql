
CREATE SEQUENCE filaespera_seq;

CREATE TABLE FilaEspera (
                id NUMERIC NOT NULL DEFAULT nextval('filaespera_seq'),
                nome VARCHAR(50) NOT NULL,
                dataCadastro DATE NOT NULL,
                dataNascimento DATE NOT NULL,
                telefone NUMERIC,
                encaminhamento VARCHAR(200),
                desistencia BOOLEAN NOT NULL,
				observacao VARCHAR(200),
                CONSTRAINT filaespera_pk PRIMARY KEY (id)
);


ALTER SEQUENCE filaespera_seq OWNED BY FilaEspera.id;

CREATE TABLE Usuario (
                matricula VARCHAR(20) NOT NULL,
                password VARCHAR(15) NOT NULL,
                email VARCHAR(30) NOT NULL,
                active BOOLEAN NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (matricula)
);


CREATE TABLE Perfil (
                matricula VARCHAR(20) NOT NULL,
                funcao VARCHAR(50) NOT NULL,
                CONSTRAINT matricula_pk PRIMARY KEY (matricula)
);


CREATE TABLE Funcionario (
                matricula VARCHAR(20) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                dataAdmissao DATE,
                CONSTRAINT funcionario_pk PRIMARY KEY (matricula)
);


CREATE TABLE Area (
                codigoArea INTEGER NOT NULL,
                nome VARCHAR(50) NOT NULL,
                CONSTRAINT area_pk PRIMARY KEY (codigoArea)
);


CREATE TABLE Servico (
                codigoServico INTEGER NOT NULL,
                nome VARCHAR(200) NOT NULL,
                codigoArea INTEGER NOT NULL,
                CONSTRAINT servico_pk PRIMARY KEY (codigoServico)
);


CREATE TABLE Professor (
                matricula VARCHAR(20) NOT NULL,
                conselhoProfissional VARCHAR(50) NOT NULL,
                profissao VARCHAR(50) NOT NULL,
                titulacao VARCHAR(50) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                codigoServico INTEGER NOT NULL,
                CONSTRAINT professor_pk PRIMARY KEY (matricula)
);


CREATE TABLE Estagiario (
                matricula VARCHAR(20) NOT NULL,
                nome VARCHAR(50) NOT NULL,
                curso VARCHAR(50) NOT NULL,
                dataInicioVigencia DATE NOT NULL,
                dataFimVigencia DATE,
                comentarios VARCHAR(500) NOT NULL,
                orientador VARCHAR(20),
                CONSTRAINT estagiario_pk PRIMARY KEY (matricula)
);


CREATE TABLE Paciente (
                numeroProntuario NUMERIC NOT NULL,
                dataEntrada DATE NOT NULL,
                dataSaida DATE,
                motivoSaida VARCHAR(200),
                origem VARCHAR(500),
                decisao VARCHAR(500),
                ativo BOOLEAN NOT NULL,
                comentarios VARCHAR(500),
                matricula VARCHAR(20),
                CONSTRAINT paciente_pk PRIMARY KEY (numeroProntuario)
);


CREATE TABLE Endereco (
                numeroProntuario NUMERIC NOT NULL,
                rua VARCHAR(100) NOT NULL,
                tipoEndereco VARCHAR(20) NOT NULL,
                logradouro VARCHAR(50) NOT NULL,
                numero INTEGER,
                complemento VARCHAR(10),
                bairro VARCHAR(50) NOT NULL,
                municipio VARCHAR(50) NOT NULL,
                uf VARCHAR(30) NOT NULL,
                cep INTEGER,
                CONSTRAINT endereco_pk PRIMARY KEY (numeroProntuario)
);


CREATE TABLE Registro (
                numeroProntuario NUMERIC NOT NULL,
                termoConsentimento VARCHAR(300) NOT NULL,
                declaracao VARCHAR(300) NOT NULL,
                CONSTRAINT registro_pk PRIMARY KEY (numeroProntuario)
);


CREATE TABLE Evolucao (
                codigoServico INTEGER NOT NULL,
                numeroProntuario NUMERIC NOT NULL,
                data timestamp NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                validado BOOLEAN NOT NULL,
                descricaoAvaliacao VARCHAR(500),
                estagiario VARCHAR(20) NOT NULL,
                professor VARCHAR(20),
                CONSTRAINT evolucao_pk PRIMARY KEY (codigoServico, numeroProntuario, data)
);


CREATE TABLE DadosPessoais (
                numeroProntuario NUMERIC NOT NULL,
                cpf NUMERIC,
                nome VARCHAR(50) NOT NULL,
                rg NUMERIC,
                dataNascimento DATE NOT NULL,
                naturalidade VARCHAR(50),
                uf VARCHAR(30),
                nacionalidade VARCHAR(50),
                sexo VARCHAR(1) NOT NULL,
                estadoCivil VARCHAR(20) NOT NULL,
                telefone NUMERIC,
                celular NUMERIC,
                telefoneRecado NUMERIC,
                CONSTRAINT dadospessoais_pk PRIMARY KEY (numeroProntuario)
);


ALTER TABLE Perfil ADD CONSTRAINT usuario_perfil_fk
FOREIGN KEY (matricula)
REFERENCES Usuario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Funcionario ADD CONSTRAINT usuario_funcionario_fk
FOREIGN KEY (matricula)
REFERENCES Usuario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Estagiario ADD CONSTRAINT usuario_estagiario_fk
FOREIGN KEY (matricula)
REFERENCES Usuario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Professor ADD CONSTRAINT usuario_professor_fk
FOREIGN KEY (matricula)
REFERENCES Usuario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Servico ADD CONSTRAINT area_servico_fk
FOREIGN KEY (codigoArea)
REFERENCES Area (codigoArea)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Evolucao ADD CONSTRAINT servico_evolucao_fk
FOREIGN KEY (codigoServico)
REFERENCES Servico (codigoServico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Professor ADD CONSTRAINT servico_professor_fk
FOREIGN KEY (codigoServico)
REFERENCES Servico (codigoServico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Evolucao ADD CONSTRAINT professor_evolucao_fk
FOREIGN KEY (professor)
REFERENCES Professor (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Estagiario ADD CONSTRAINT professor_estagiario_fk
FOREIGN KEY (orientador)
REFERENCES Professor (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Evolucao ADD CONSTRAINT estagiario_evolucao_fk
FOREIGN KEY (estagiario)
REFERENCES Estagiario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Paciente ADD CONSTRAINT estagiario_paciente_fk
FOREIGN KEY (matricula)
REFERENCES Estagiario (matricula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE DadosPessoais ADD CONSTRAINT paciente_dadospessoais_fk
FOREIGN KEY (numeroProntuario)
REFERENCES Paciente (numeroProntuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Registro ADD CONSTRAINT paciente_prontuario_fk
FOREIGN KEY (numeroProntuario)
REFERENCES Paciente (numeroProntuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Endereco ADD CONSTRAINT paciente_endereco_fk
FOREIGN KEY (numeroProntuario)
REFERENCES Paciente (numeroProntuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE Evolucao ADD CONSTRAINT prontuario_evolucao_fk
FOREIGN KEY (numeroProntuario)
REFERENCES Registro (numeroProntuario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;



-- Add permission for all tables 
GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO aluno;