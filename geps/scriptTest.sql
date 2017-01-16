delete from realizaServico;
delete from acompanha;
delete from evolucao;
delete from estagiario;
delete from usuario;
delete from prontuario;
delete from paciente;
delete from dadospessoais;

--pacientes
insert into dadospessoais (cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado, ativo) 
values(1234567890, 'teste1', 1111111111, '2003-02-01', 'santa rita', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into dadospessoais (cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado, ativo) 
values(2345678901, 'teste2', 2222222222, '2004-02-01', 'santa rita', 'minas gerais', 'brasileira', 'F', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into dadospessoais (cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado, ativo) 
values(23456789012, 'teste3', 3333333333, '2005-02-01', 'santa rita', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into paciente (cpf, decisao, origem, dataentrada, datasaida) values(1234567890, 1, 1, '2016-02-01', null);
insert into paciente (cpf, decisao, origem, dataentrada, datasaida) values(2345678901, 1, 1, '2016-10-15', null);
insert into paciente (cpf, decisao, origem, dataentrada, datasaida) values(23456789012, 1, 1, '2017-01-01', null);


--estagiarios
insert into dadospessoais (cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado, ativo) 
values(99999999999, 'teste9', 9999999999, '1998-10-20', 'pouso alegre', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into dadospessoais (cpf, nome, rg, datanascimento, naturalidade, uf, nacionalidade, sexo, estadocivil, telefone, celular, telefonerecado, ativo) 
values(88888888888, 'teste8', 8888888888, '1997-11-30', 'pouso alegre', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into usuario (userName, password, email, active) values ('estag01', 'estag01', 'estag01@univas.edu.br', true);
insert into usuario (userName, password, email, active) values ('estag02', 'estag02', 'estag02@univas.edu.br', true);

insert into estagiario (cpf, matricula, curso, areaDeEstagio, dataInicioVigencia, dataFimVigencia, comentarios, userName)
values(99999999999, 1, 'curso 1', 'área 1', '2017-01-01', '2017-12-31', 'comentários 1', 'estag01');

insert into estagiario (cpf, matricula, curso, areaDeEstagio, dataInicioVigencia, dataFimVigencia, comentarios, userName)
values(88888888888, 2, 'curso 1', 'área 2', '2016-06-01', '2017-06-30', 'comentários 2', 'estag02');

--realiza servico
insert into realizaServico (cpf, codigoServico) values (99999999999, 11);
insert into realizaServico (cpf, codigoServico) values (99999999999, 33);
insert into realizaServico (cpf, codigoServico) values (99999999999, 55);

insert into realizaServico (cpf, codigoServico) values (88888888888, 22);
insert into realizaServico (cpf, codigoServico) values (88888888888, 33);
insert into realizaServico (cpf, codigoServico) values (88888888888, 44);

select * from acompanha;

