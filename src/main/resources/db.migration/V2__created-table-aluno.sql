CREATE TABLE aluno
(
    id_aluno           BIGINT       NOT NULL AUTO_INCREMENT,
    foto               TEXT,
    nome               VARCHAR(255) NOT NULL,
    data_de_nascimento DATE         NOT NULL,
    email              VARCHAR(255) NOT NULL,
    telefone           VARCHAR(25)  NOT NULL,

    PRIMARY KEY (id_aluno)
);
