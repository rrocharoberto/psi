CREATE TABLE "public"."tb_usuario"
(
   id_usuario varchar(255) PRIMARY KEY NOT NULL,
   ds_senha varchar(255),
   ds_login varchar(255)
);

CREATE UNIQUE INDEX tb_usuario_pkey ON tb_usuario(id_usuario);


CREATE TABLE "public"."tb_pessoa"
(
   id_pessoa int PRIMARY KEY NOT NULL,
   dt_cadastro timestamp,
   ds_email varchar(255),
   ds_endereco varchar(255),
   nm_pessoa varchar(255),
   fl_origemcadastro varchar(255),
   fl_sexo varchar(255),
   id_usuario_cadastro varchar(255)
);

ALTER TABLE tb_pessoa
ADD CONSTRAINT fk_c9463uj94vca0385rhh9krd4s
FOREIGN KEY (id_usuario_cadastro)
REFERENCES tb_usuario(id_usuario);

CREATE UNIQUE INDEX tb_pessoa_pkey ON tb_pessoa(id_pessoa);

create sequence hibernate_sequence start with 1;

insert into tb_usuario (id_usuario, ds_senha, ds_login) values (0, 'roberto', 'roberto');

insert into tb_pessoa ("id_pessoa", "dt_cadastro", "ds_email", "ds_endereco", "nm_pessoa", "fl_origemcadastro", "fl_sexo", "id_usuario_cadastro") 
values(0, '2016-10-08', 'roberto@gmail.com', 'rua x', 'Roberto Rocha', 'origemCadastro', 'M', '0');


select * from tb_pessoa;
select * from tb_usuario;
