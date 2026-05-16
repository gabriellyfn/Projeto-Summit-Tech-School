CREATE TABLE turma
(
    id_turma      BIGINT      NOT NULL AUTO_INCREMENT,
    nome_da_turma VARCHAR(50) NOT NULL,
    turno         VARCHAR(5)  NOT NULL,
    ano           VARCHAR(4),
    semestre      VARCHAR(30),

    PRIMARY KEY (id_turma)
);