insert into "public"."dadospessoais" ("cpf", "nome", "rg", "datanascimento", "naturalidade", "uf", "nacionalidade", "sexo", "estadocivil", "telefone", "celular", "telefonerecado", "ativo") 
values(1234567890, 'teste1', 1111111111, '2003-02-01', 'santa rita', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into "public"."dadospessoais" ("cpf", "nome", "rg", "datanascimento", "naturalidade", "uf", "nacionalidade", "sexo", "estadocivil", "telefone", "celular", "telefonerecado", "ativo") 
values(2345678901, 'teste2', 2222222222, '2004-02-01', 'santa rita', 'minas gerais', 'brasileira', 'F', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into "public"."dadospessoais" ("cpf", "nome", "rg", "datanascimento", "naturalidade", "uf", "nacionalidade", "sexo", "estadocivil", "telefone", "celular", "telefonerecado", "ativo") 
values(23456789012, 'teste3', 3333333333, '2005-02-01', 'santa rita', 'minas gerais', 'brasileira', 'M', 'solteiro', 3534567890, 35988887777, null, 'true');

insert into "public"."paciente" ("cpf", "decisao", "origem", "dataentrada", "datasaida") values(1234567890, 1, 1, '2016-02-01', null);
insert into "public"."paciente" ("cpf", "decisao", "origem", "dataentrada", "datasaida") values(2345678901, 1, 1, '2016-10-15', null);
insert into "public"."paciente" ("cpf", "decisao", "origem", "dataentrada", "datasaida") values(23456789012, 1, 1, '2017-01-01', null);
