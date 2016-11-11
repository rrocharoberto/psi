
CREATE TABLE public.DadosPessoais (
                cpf NUMERIC NOT NULL,
                nome VARCHAR(50) NOT NULL,
                rg NUMERIC NOT NULL,
                dataNascimento DATE NOT NULL,
                naturalidade VARCHAR(50) NOT NULL,
                uf VARCHAR(30) NOT NULL,
                nacionalidade VARCHAR(50) NOT NULL,
                sexo VARCHAR(1) NOT NULL,
                estadoCivil VARCHAR(20) NOT NULL,
                telefone NUMERIC,
                celular NUMERIC,
                telefoneRecado NUMERIC,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT dadospessoais_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Usuario (
                userName VARCHAR(20) NOT NULL,
                password VARCHAR(15) NOT NULL,
                email VARCHAR(30) NOT NULL,
                active BOOLEAN NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (userName)
);


CREATE TABLE public.Funcionario (
                cpf NUMERIC NOT NULL,
                dataAdmissao DATE NOT NULL,
                userName VARCHAR(20) NOT NULL,
                CONSTRAINT funcionario_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Convenio (
                codigo INTEGER NOT NULL,
                nome VARCHAR(50) NOT NULL,
                CONSTRAINT convenio_pk PRIMARY KEY (codigo)
);


CREATE TABLE public.Professor (
                cpf NUMERIC NOT NULL,
                conselhoProfissional VARCHAR(50) NOT NULL,
                registro VARCHAR(50) NOT NULL,
                profissao VARCHAR(50) NOT NULL,
                titulacao VARCHAR(50) NOT NULL,
                userName VARCHAR(20) NOT NULL,
                CONSTRAINT professor_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Servico (
                codigo INTEGER NOT NULL,
                nome VARCHAR(200) NOT NULL,
                CONSTRAINT servico_pk PRIMARY KEY (codigo)
);


CREATE TABLE public.SupervisionaServico (
                cpf NUMERIC NOT NULL,
                codigo INTEGER NOT NULL,
                CONSTRAINT supervisionaservico_pk PRIMARY KEY (cpf, codigo)
);


CREATE TABLE public.Estagiario (
                cpf NUMERIC NOT NULL,
                matricula INTEGER NOT NULL,
                curso VARCHAR(50) NOT NULL,
                areaDeEstagio VARCHAR(50) NOT NULL,
                dataInicioVigencia DATE NOT NULL,
                dataFimVigencia DATE,
                comentarios VARCHAR(500) NOT NULL,
                userName VARCHAR(20) NOT NULL,
                CONSTRAINT estagiario_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Avalia (
                cpfEstagiario NUMERIC NOT NULL,
                cpfProfessor NUMERIC NOT NULL,
                codigo INTEGER NOT NULL,
                CONSTRAINT avalia_pk PRIMARY KEY (cpfEstagiario, cpfProfessor, codigo)
);


CREATE TABLE public.RealizaServico (
                cpf NUMERIC NOT NULL,
                codigo INTEGER NOT NULL,
                CONSTRAINT realizaservico_pk PRIMARY KEY (cpf, codigo)
);


CREATE TABLE public.Endereco (
                cpf NUMERIC NOT NULL,
                rua VARCHAR(100) NOT NULL,
                tipoEndereco VARCHAR(20) NOT NULL,
                logradouro VARCHAR(50) NOT NULL,
                numero INTEGER,
                complemento VARCHAR(10),
                bairro VARCHAR(50) NOT NULL,
                municipio VARCHAR(50) NOT NULL,
                uf VARCHAR(30) NOT NULL,
                cep INTEGER NOT NULL,
                CONSTRAINT endereco_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Paciente (
                cpf NUMERIC NOT NULL,
                decisao INTEGER NOT NULL,
                origem INTEGER NOT NULL,
                dataEntrada DATE NOT NULL,
                dataSaida DATE,
                CONSTRAINT paciente_pk PRIMARY KEY (cpf)
);


CREATE TABLE public.Prontuario (
                numero INTEGER NOT NULL,
                cpf NUMERIC NOT NULL,
                termoConsentimento VARCHAR(300) NOT NULL,
                declaracao VARCHAR(300) NOT NULL,
                dataFechamento DATE,
                motivoFechamento VARCHAR(50),
                comentarios VARCHAR(500),
                CONSTRAINT prontuario_pk PRIMARY KEY (numero)
);


CREATE TABLE public.Evolucao (
                data DATE NOT NULL,
                numero INTEGER NOT NULL,
                cpfEstagiario NUMERIC NOT NULL,
                descricao VARCHAR(500) NOT NULL,
                cpfProfessor NUMERIC,
                avaliacao INTEGER,
                descricaoAvaliacao VARCHAR(500),
                CONSTRAINT evolucao_pk PRIMARY KEY (data, numero, cpfEstagiario)
);


CREATE TABLE public.Acompanha (
                numero INTEGER NOT NULL,
                cpf NUMERIC NOT NULL,
                dataInicio DATE NOT NULL,
                ativo BOOLEAN NOT NULL,
                CONSTRAINT acompanha_pk PRIMARY KEY (numero, cpf, dataInicio)
);


ALTER TABLE public.Paciente ADD CONSTRAINT dadospessoais_paciente_fk
FOREIGN KEY (cpf)
REFERENCES public.DadosPessoais (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Endereco ADD CONSTRAINT dadospessoais_endereco_fk
FOREIGN KEY (cpf)
REFERENCES public.DadosPessoais (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Professor ADD CONSTRAINT dadospessoais_professor_fk
FOREIGN KEY (cpf)
REFERENCES public.DadosPessoais (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Estagiario ADD CONSTRAINT dadospessoais_estagiario_fk
FOREIGN KEY (cpf)
REFERENCES public.DadosPessoais (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Funcionario ADD CONSTRAINT dadospessoais_funcionario_fk
FOREIGN KEY (cpf)
REFERENCES public.DadosPessoais (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Estagiario ADD CONSTRAINT usuario_estagiario_fk
FOREIGN KEY (userName)
REFERENCES public.Usuario (userName)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Professor ADD CONSTRAINT usuario_professor_fk
FOREIGN KEY (userName)
REFERENCES public.Usuario (userName)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Funcionario ADD CONSTRAINT usuario_funcionario_fk
FOREIGN KEY (userName)
REFERENCES public.Usuario (userName)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Servico ADD CONSTRAINT convenio_servico_fk
FOREIGN KEY (codigo)
REFERENCES public.Convenio (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Evolucao ADD CONSTRAINT professor_evolucao_fk
FOREIGN KEY (cpfProfessor)
REFERENCES public.Professor (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Avalia ADD CONSTRAINT professor_avalia_fk
FOREIGN KEY (cpfProfessor)
REFERENCES public.Professor (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.SupervisionaServico ADD CONSTRAINT professor_supervisionaservico_fk
FOREIGN KEY (cpf)
REFERENCES public.Professor (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.RealizaServico ADD CONSTRAINT servico_realizaservico_fk
FOREIGN KEY (codigo)
REFERENCES public.Servico (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Avalia ADD CONSTRAINT servico_avalia_fk
FOREIGN KEY (codigo)
REFERENCES public.Servico (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.SupervisionaServico ADD CONSTRAINT servico_supervisionaservico_fk
FOREIGN KEY (codigo)
REFERENCES public.Servico (codigo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Evolucao ADD CONSTRAINT estagiario_evolucao_fk
FOREIGN KEY (cpfEstagiario)
REFERENCES public.Estagiario (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Acompanha ADD CONSTRAINT estagiario_acompanha_fk
FOREIGN KEY (cpf)
REFERENCES public.Estagiario (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.RealizaServico ADD CONSTRAINT estagiario_realizaservico_fk
FOREIGN KEY (cpf)
REFERENCES public.Estagiario (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Avalia ADD CONSTRAINT estagiario_avalia_fk
FOREIGN KEY (cpfEstagiario)
REFERENCES public.Estagiario (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Prontuario ADD CONSTRAINT paciente_prontuario_fk
FOREIGN KEY (cpf)
REFERENCES public.Paciente (cpf)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Acompanha ADD CONSTRAINT prontuario_acompanha_fk
FOREIGN KEY (numero)
REFERENCES public.Prontuario (numero)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.Evolucao ADD CONSTRAINT prontuario_evolucao_fk
FOREIGN KEY (numero)
REFERENCES public.Prontuario (numero)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;