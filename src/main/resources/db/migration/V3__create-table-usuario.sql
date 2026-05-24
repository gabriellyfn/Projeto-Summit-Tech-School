CREATE TABLE usuario
(
    id_usuario         BIGINT       NOT NULL AUTO_INCREMENT,
    nome               VARCHAR(150) NOT NULL,
    foto               TEXT,
    login              VARCHAR(155) NOT NULL UNIQUE,
    senha              VARCHAR(155) NOT NULL,
    telefone           VARCHAR(25)  NOT NULL UNIQUE,
    email              VARCHAR(255) NOT NULL UNIQUE,
    data_de_nascimento DATE,
    id_perfil          BIGINT       NOT NULL,

    PRIMARY KEY (id_usuario),
    CONSTRAINT fk_usuario_perfil FOREIGN KEY (id_perfil) REFERENCES perfil (id_perfil)
);
