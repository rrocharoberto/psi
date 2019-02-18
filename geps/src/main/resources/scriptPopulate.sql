

INSERT INTO USUARIO (matricula, password, email, active) VALUES ('admin', 'admin', 'admin@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('andre', 'andre', 'andre@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('prof1', 'prof', 'p1@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('prof2', 'prof', 'p2@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('prof3', 'prof', 'p3@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag1', 'estag', 'e1@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag2', 'estag', 'e2@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag3', 'estag', 'e3@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag4', 'estag', 'e4@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag5', 'estag', 'e5@univas.edu.br', true);
INSERT INTO USUARIO (matricula, password, email, active) VALUES ('estag6', 'estag', 'e5@univas.edu.br', true);

INSERT INTO PERFIL (matricula, funcao) VALUES ('admin', 'ADMIN');
INSERT INTO PERFIL (matricula, funcao) VALUES ('andre', 'FUNCIONARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('prof1', 'PROFESSOR');
INSERT INTO PERFIL (matricula, funcao) VALUES ('prof2', 'PROFESSOR');
INSERT INTO PERFIL (matricula, funcao) VALUES ('prof3', 'PROFESSOR');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag1', 'ESTAGIARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag2', 'ESTAGIARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag3', 'ESTAGIARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag4', 'ESTAGIARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag5', 'ESTAGIARIO');
INSERT INTO PERFIL (matricula, funcao) VALUES ('estag6', 'ESTAGIARIO');

INSERT INTO area (codigoarea, nome) VALUES (1, 'Área 001');
INSERT INTO area (codigoarea, nome) VALUES (2, 'Área 002');

INSERT INTO servico (codigoservico, nome, codigoarea) VALUES (11, 'Serviço 11', 1);
INSERT INTO servico (codigoservico, nome, codigoarea) VALUES (12, 'Serviço 12', 1);

INSERT INTO professor (matricula, conselhoprofissional, profissao, titulacao, nome, codigoservico) 
VALUES ('prof1', 'aaa', 'profisão 1', 'aaa', 'Roberto', 11);
INSERT INTO professor (matricula, conselhoprofissional, profissao, titulacao, nome, codigoservico) 
VALUES ('prof2', 'bbb', 'profisão 2', 'bbb', 'Rodrigo', 12);
INSERT INTO professor (matricula, conselhoprofissional, profissao, titulacao, nome, codigoservico) 
VALUES ('prof3', 'ccc', 'profisão 3', 'bbb', 'Alaíde', 12);


INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag1', 'e1', 'curso 1', '2018-07-02', '2019-08-23', 'comentarios 1', 'prof1', NULL);
INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag2', 'e2', 'curso 2', '2018-07-11', '2019-08-29', 'comentarios 2', 'prof2', NULL);
INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag3', 'e3', 'curso 3', '2018-07-15', '2019-09-29', 'comentarios 3', 'prof2', NULL);
INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag4', 'e4', 'curso 1', '2018-07-20', '2019-10-29', 'comentarios 4', 'prof3', NULL);
INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag5', 'e5', 'curso 2', '2018-07-25', '2019-11-29', 'comentarios 5', 'prof3', NULL);
INSERT INTO estagiario (matricula, nome, curso, datainiciovigencia, datafimvigencia, comentarios, orientador, telefone)
VALUES ('estag6', 'e6', 'curso 3', '2018-07-30', '2019-12-29', 'comentarios 6', 'prof3', 3598765432);

INSERT INTO paciente (numeroprontuario, dataentrada, datasaida, motivosaida, origem, decisao, ativo, comentarios, matricula)
VALUES ('0', '2018-08-01', NULL, NULL, 'Origem 1', NULL, true, NULL, 'estag1');

INSERT INTO dadospessoais (numeroprontuario, cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado)
VALUES ('0', 11111111111, 'paciente 1', 11111111, '2018-08-07', 'nat', 'AP', 'nac', 'M', '0', 2222222222, NULL, NULL);

INSERT INTO registro (numeroprontuario, termoconsentimento, declaracao) VALUES ('0', '01 - Introdução aos Bancos de Dados.pdf', '3.png');

INSERT INTO evolucao(codigoservico, numeroprontuario, data, descricao, validado, descricaoavaliacao, professor, estagiario1, estagiario2)
VALUES (11, '0', '2018-08-30', 'Evolução 1', true, 'Descrição da evolução 1', 'prof1', 'estag1', null);
INSERT INTO evolucao(codigoservico, numeroprontuario, data, descricao, validado, descricaoavaliacao, professor, estagiario1, estagiario2)
VALUES (11, '0', '2019-02-12', 'Evolução 2', true, 'Descrição da evolução 2', 'prof2', 'estag2', 'estag3');


INSERT INTO FilaEspera (id, nome, dataCadastro, dataNascimento, telefone, encaminhamento, desistencia, observacao)
VALUES (1, 'Paciente 1', '2019-02-10', '2000-01-01', 35998765432, NULL, false, NULL);

INSERT INTO FilaEspera (id, nome, dataCadastro, dataNascimento, telefone, encaminhamento, desistencia, observacao)
VALUES (2, 'Paciente 2 Paciente 2 Paciente 2 Paciente 2 Pacie', '2019-01-10', '2000-02-01', 3543219876, NULL, false, NULL);
