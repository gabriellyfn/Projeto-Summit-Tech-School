-- ------------------------------------------------------------------------------
-- 1. Populando a tabela de Perfis de Usuário
-- ------------------------------------------------------------------------------
INSERT INTO perfil (nome_perfil) VALUES ('ADMIN');
INSERT INTO perfil (nome_perfil) VALUES ('PROFESSOR');
INSERT INTO perfil (nome_perfil) VALUES ('COORDENADOR');
INSERT INTO perfil (nome_perfil) VALUES ('ANALISTA_DE_QUALIDADE');
INSERT INTO perfil (nome_perfil) VALUES ('PROFESSOR_ADMINISTRATIVO');



-- ------------------------------------------------------------------------------
-- 2. Populando a tabela de Turmas
-- ------------------------------------------------------------------------------
INSERT INTO turma (nome_da_turma, turno, ano, semestre) VALUES
('1A - Ensino Médio', 'MANHA', '2024', 'PRIMEIRO_SEMESTRE'),
('1B - Ensino Médio', 'MANHA', '2024', 'PRIMEIRO_SEMESTRE'),
('2A - Ensino Médio', 'TARDE', '2024', 'PRIMEIRO_SEMESTRE'),
('2B - Ensino Médio', 'TARDE', '2024', 'PRIMEIRO_SEMESTRE'),
('3A - Ensino Médio', 'NOITE', '2024', 'SEGUNDO_SEMESTRE'),
('3B - Ensino Médio', 'NOITE', '2024', 'SEGUNDO_SEMESTRE');


-- ------------------------------------------------------------------------------
-- 3. Populando a tabela de Tipos de Ocorrência
-- ------------------------------------------------------------------------------
INSERT INTO tipo_ocorrencia (nome_ocorrencia) VALUES
('ADMINISTRATIVO'),
('DISCIPLINAR'),
('PEDAGOGICA'),
('MEDICA'),
('ACADEMICA'),
('COMPORTAMENTAL'),
('FINANCEIRA'),
('OPERACIONAL'),
('PSICOLOGICA'),
('INCIDENTE'),
('INDISCIPLINAR'),
('ATRASO'),
('RENDIMENTO');


-- ------------------------------------------------------------------------------
-- 4. Populando a tabela de Alunos
-- ------------------------------------------------------------------------------
INSERT INTO aluno (nome, data_de_nascimento, email, telefone) VALUES
('Ana Beatriz', '2008-03-14', 'ana.beatriz@example.com', '11911110001'),
('Bruno Cesar', '2009-01-20', 'bruno.cesar@example.com', '11911110002'),
('Clara Dias', '2008-07-07', 'clara.dias@example.com', '11911110003'),
('Daniel Alves', '2009-05-25', 'daniel.alves@example.com', '11911110004'),
('Eduarda Ferreira', '2008-09-30', 'eduarda.ferreira@example.com', '11911110005'),
('Felipe Barros', '2009-11-11', 'felipe.barros@example.com', '11911110006'),
('Gabriela Lima', '2008-02-18', 'gabriela.lima@example.com', '11911110007'),
('Heitor Rocha', '2009-04-03', 'heitor.rocha@example.com', '11911110008'),
('Isabela Pinto', '2008-06-22', 'isabela.pinto@example.com', '11911110009'),
('João Victor', '2009-12-01', 'joao.victor@example.com', '11911110010');


-- ------------------------------------------------------------------------------
-- 5. Populando a tabela de Usuários
-- ------------------------------------------------------------------------------
INSERT INTO usuario (nome, login, senha, id_perfil, email, data_de_nascimento, telefone) VALUES
('admin', 'admin', 'admin', 1, 'admin@admin.com', '1980-01-01', '11922220012' ),
('Prof. Carlos Andrade', 'carlos.prof', 'senha123', 1, 'carlos.andrade@example.com', '1980-01-01', '11922220001'),
('Prof.ª Beatriz Souza', 'beatriz.prof', 'senha123', 1, 'beatriz.souza@example.com', '1985-02-02', '11922220002'),
('Prof. Marcos Lima', 'marcos.prof', 'senha123', 1, 'marcos.lima@example.com', '1990-03-03', '11922220003'),
('Coord. Lúcia Martins', 'lucia.coord', 'senha456', 2, 'lucia.martins@example.com', '1975-04-04', '11922220004'),
('Coord. Pedro Gomes', 'pedro.coord', 'senha456', 2, 'pedro.gomes@example.com', '1978-05-05', '11922220005'),
('Analista Fábio Jr', 'fabio.qa', 'senha789', 3, 'fabio.jr@example.com', '1992-06-06', '11922220006'),
('Prof.ª/Adm. Sofia Reis', 'sofia.admin', 'senha123', 4, 'sofia.reis@example.com', '1988-07-07', '11922220007'),
('Prof. Ricardo Eletro', 'ricardo.prof', 'senha123', 1, 'ricardo.eletro@example.com', '1981-08-08', '11922220008'),
('Prof.ª Mônica Geller', 'monica.prof', 'senha123', 1, 'monica.geller@example.com', '1983-09-09', '11922220009'),
('Diretor Silvio Santos', 'silvio.dir', 'senha456', 2, 'silvio.santos@example.com', '1960-10-10', '11922220010'),
('Prof. Ramon Abate Abel', 'Abate.Abelzinho', '$2a$10$jzbIPp0IuaYnaMPZxqALeufmjSDOwCdrcf35i70nyeZMGQXt7UJV.', 5, 'abate.abel@cbf.com', '1990-05-01', '11922220015');
('admin', 'admin', '$2a$10$6bIiMq6akzxvGj5OE0hhCeTQvsswigp8H57v5nsoEoDeCZ/CuG30y', 1, 'admin@admin.com', '1980-01-01', '11922220012' ),
('Prof.ª Beatriz Souza', 'beatriz.prof', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 1, 'beatriz.souza@example.com', '1985-02-02', '11922220002'),
('Prof. Marcos Lima', 'marcos.prof', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 1, 'marcos.lima@example.com', '1990-03-03', '11922220003'),
('Coord. Lúcia Martins', 'lucia.coord', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 2, 'lucia.martins@example.com', '1975-04-04', '11922220004'),
('Coord. Pedro Gomes', 'pedro.coord', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 2, 'pedro.gomes@example.com', '1978-05-05', '11922220005'),
('Analista Fábio Jr', 'fabio.qa', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 3, 'fabio.jr@example.com', '1992-06-06', '11922220006'),
('Prof.ª/Adm. Sofia Reis', 'sofia.admin', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 4, 'sofia.reis@example.com', '1988-07-07', '11922220007'),
('Prof. Ricardo Eletro', 'ricardo.prof', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 1, 'ricardo.eletro@example.com', '1981-08-08', '11922220008'),
('Prof.ª Mônica Geller', 'monica.prof', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 1, 'monica.geller@example.com', '1983-09-09', '11922220009'),
('Diretor Silvio Santos', 'silvio.dir', '$2a$10$uj5WMCHd0df.qOYMs4KRxO6X5NelqmapWJ0j5ET3bIO1j1E4fqqGS', 2, 'silvio.santos@example.com', '1960-10-10', '11922220010');



-- ------------------------------------------------------------------------------
-- 6. Matriculando Alunos em Turmas
-- ------------------------------------------------------------------------------
INSERT INTO aluno_turma (id_aluno, id_turma) VALUES
(1, 1),
(2, 1),
(3, 2),
(4, 2),
(5, 3),
(6, 3),
(7, 4),
(8, 5),
(9, 5),
(10, 6);


-- ------------------------------------------------------------------------------
-- 7. Gerando Ocorrências
-- ------------------------------------------------------------------------------
INSERT INTO ocorrencia (id_aluno, id_usuario, id_turma, id_tipo_ocorrencia, data, hora, categoria_ocorrencia, descricao) VALUES
(1, 1, 1, 2, CURDATE(), '09:30:00', 'DISCIPLINAR', 'Aluna Ana Beatriz conversando excessivamente durante a aula de Matemática.'),
(2, 1, 1, 12, CURDATE(), '07:35:00', 'ATRASO', 'Aluno Bruno Cesar chegou 35 minutos atrasado.'),
(3, 2, 2, 5, '2024-05-10', '11:00:00', 'ACADEMICA', 'Aluna Clara Dias não entregou o trabalho de História.'),
(4, 3, 2, 13, '2024-05-15', '14:00:00', 'RENDIMENTO', 'Aluno Daniel Alves apresentou nota baixa na avaliação de Geografia.'),
(5, 4, 3, 2, '2024-05-18', '16:20:00', 'INDISCIPLINAR', 'Aluna Eduarda Ferreira se recusou a seguir as instruções da coordenação.'),
(6, 7, 3, 5, CURDATE(), '08:00:00', 'ACADEMICA', 'Aluno Felipe Barros com excelente desempenho na feira de ciências.'),
(7, 8, 4, 12, '2024-05-05', '13:15:00', 'ATRASO', 'Aluna Gabriela Lima com atrasos recorrentes.'),
(8, 9, 5, 2, '2024-04-30', '20:00:00', 'DISCIPLINAR', 'Uso de celular em sala pelo aluno Heitor Rocha.'),
(9, 10, 5, 13, '2024-05-20', '21:00:00', 'RENDIMENTO', 'Reunião com os pais da aluna Isabela Pinto sobre baixo rendimento.'),
(10, 1, 6, 5, '2024-05-21', '10:00:00', 'ACADEMICA', 'Aluno João Victor ajudou colegas com dificuldade na matéria.');