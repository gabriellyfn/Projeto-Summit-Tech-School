CREATE TABLE aluno_turma
(
    id_aluno_turma BIGINT NOT NULL AUTO_INCREMENT,
    id_aluno       BIGINT NOT NULL,
    id_turma       BIGINT NOT NULL,

    PRIMARY KEY (id_aluno_turma),
    CONSTRAINT fk_aluno_turma_aluno FOREIGN KEY (id_aluno) REFERENCES aluno (id_aluno),
    CONSTRAINT fk_aluno_turma_turma FOREIGN KEY (id_turma) REFERENCES turma (id_turma),
    CONSTRAINT uk_aluno_turma UNIQUE (id_aluno, id_turma)
);