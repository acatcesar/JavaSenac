DROP DATABASE IF EXISTS DBSOCORRODESK;
CREATE DATABASE DBSOCORRODESK;

USE DBSOCORRODESK;

CREATE TABLE TIPOUSUARIO (
IDTIPOUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
, DESCRICAO VARCHAR(255)
);

CREATE TABLE USUARIO (
IDUSUARIO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
, IDTIPOUSUARIO INT, FOREIGN KEY (IDTIPOUSUARIO) REFERENCES TIPOUSUARIO (IDTIPOUSUARIO)
, NOME VARCHAR(255)
, CPF CHAR(11) 
, EMAIL VARCHAR(255)
, DATACADASTRO DATE
, DATAEXPIRACAO DATE
, LOGIN VARCHAR(255)
, SENHA VARCHAR(255)
);

CREATE TABLE CHAMADOS (
IDCHAMADO INT NOT NULL PRIMARY KEY AUTO_INCREMENT
, IDUSUARIO INT, FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO (IDUSUARIO)
, IDTECNICO INT, FOREIGN KEY (IDUSUARIO) REFERENCES USUARIO (IDUSUARIO)
, TITULO VARCHAR(255)
, DESCRICAO VARCHAR(255)
, DATAABERTURA DATE 
, SOLUCAO VARCHAR(255)
, DATAFECHAMENTO DATE 
);

INSERT INTO TIPOUSUARIO (descricao) VALUES ('ADMINISTRADOR');
INSERT INTO TIPOUSUARIO (descricao) VALUES ('TECNICO');
INSERT INTO TIPOUSUARIO (descricao) VALUES ('USUARIO');

INSERT INTO USUARIO (idtipousuario, nome, cpf, email, datacadastro, dataexpiracao, login, senha) VALUES (1, 'Leonardo', '01234567890', 
		'Leonardo@email.com', now(), null, 'Leonardo', 'Garcia');
        INSERT INTO USUARIO (idtipousuario, nome, cpf, email, datacadastro, dataexpiracao, login, senha) VALUES (2, 'Leonardo', '01234567890', 
		'Leonardo@email.com', now(), null, 'Leonardo', 'Garciaa');
        INSERT INTO USUARIO (idtipousuario, nome, cpf, email, datacadastro, dataexpiracao, login, senha) VALUES (3, 'Leonardo', '01234567890', 
		'Leonardo@email.com', now(), null, 'Leonardo', 'Garciaaa');
        
        insert into chamados values (default,1,2,"Erro 121","Estou com erro pae","2000-02-02","Fechar e abrir","2000-02-03");
        
        select * from chamados;
        select * from usuario;