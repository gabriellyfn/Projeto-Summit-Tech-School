CREATE TABLE ocorrencia
(
    id_ocorrencia        BIGINT       NOT NULL AUTO_INCREMENT,
    id_turma             BIGINT       NOT NULL,
    id_aluno             BIGINT       NOT NULL,
    id_usuario           BIGINT       NOT NULL,
    id_tipo_ocorrencia   BIGINT       NOT NULL,
    data                 DATE         NOT NULL,
    hora                 TIME         NOT NULL,
    categoria_ocorrencia VARCHAR(30)  NOT NULL,
    descricao            VARCHAR(255) NOT NULL,
    ativo                TINYINT(1)   DEFAULT 1,

    PRIMARY KEY (id_ocorrencia),
    CONSTRAINT fk_id_turma FOREIGN KEY (id_turma) REFERENCES turma (id_turma),
    CONSTRAINT fk_id_aluno FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno),
    CONSTRAINT fk_id_usuario FOREIGN KEY (id_usuario) REFERENCES usuario (id_usuario),
    CONSTRAINT fk_id_tipo_ocorrencia FOREIGN KEY (id_tipo_ocorrencia) REFERENCES tipo_ocorrencia (id_tipo_ocorrencia)
);