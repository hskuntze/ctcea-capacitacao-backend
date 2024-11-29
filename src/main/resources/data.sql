INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_ADMIN');
INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_USUARIO');

INSERT INTO tb_posto (id, titulo) VALUES (1, 'Soldado');
INSERT INTO tb_posto (id, titulo) VALUES (2, 'Cabo');
INSERT INTO tb_posto (id, titulo) VALUES (3, 'Subtenente');
INSERT INTO tb_posto (id, titulo) VALUES (4, '1º Sargento');
INSERT INTO tb_posto (id, titulo) VALUES (5, '2º Sargento');
INSERT INTO tb_posto (id, titulo) VALUES (6, '3º Sargento');
INSERT INTO tb_posto (id, titulo) VALUES (7, 'Subtenente');
INSERT INTO tb_posto (id, titulo) VALUES (8, '1º Tenente');
INSERT INTO tb_posto (id, titulo) VALUES (9, '2º Tenente');
INSERT INTO tb_posto (id, titulo) VALUES (10, 'Capitão');
INSERT INTO tb_posto (id, titulo) VALUES (11, 'Major');
INSERT INTO tb_posto (id, titulo) VALUES (12, 'Tenente-Coronel');
INSERT INTO tb_posto (id, titulo) VALUES (13, 'Coronel');
INSERT INTO tb_posto (id, titulo) VALUES (14, 'General de Brigada');
INSERT INTO tb_posto (id, titulo) VALUES (15, 'General de Divisão');
INSERT INTO tb_posto (id, titulo) VALUES (16, 'General de Exército');

INSERT INTO tb_usuario (identidade, nome, sobrenome, email, senha, tipo, nome_guerra, id_posto, telefone, habilitado, registro_completo) VALUES ('3087427', 'Admin', '', 'admin@teste.com', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', 1, null, null, '+5561992515369', true, true);

INSERT INTO tb_perfil_usuario (id_usuario, id_perfil) VALUES (1, 1);

INSERT INTO tb_treinamento (
  sad, material, treinamento, tipo, subsistema, modalidade, 
  brigada, om, grupo, executor, instituicao, data_inicio, 
  data_fim, vagas, status, avaliacao_pratica, avaliacao_teorica, 
  certificado, logistica_treinamento, 
  nivelamento, carga_horaria, publico_alvo, descricao_atividade, observacoes, pre_requisitos, desc_nivelamento
)
VALUES (
  'sad3a', 'Barraca CC2 Transportável', 'Treinamento de Barraca CC2 Transportável', 3, 'CSC', 1, 
  'Vinculação direta ao CMDO', 'DESMIL', 1, 2, 'Instituição A', '2024-01-10', 
  '2024-01-15', 50, 2, 1, 1, 
  1, 'Logística do treinamento XYZ', 1, 40, 1, 'Treinamento sobre primeiros socorros com simulações práticas', '', 'Noções básicas de saúde', ''
);

INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (2, '039875', 'DECEx', '1ª RM', 'DECEx', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', '00.394.452/0270-52', 'ODS', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (3, '045732', 'DETMIL', '1ª RM', 'DECEx', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (4, '045740', 'DEPA', '1ª RM', 'DECEx', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (5, '045724', 'DESMIL', '1ª RM', 'DECEx', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (6, '046128', 'DPHCEx', '1ª RM', 'DECEx', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (7, '000414', 'M N M S G M', '1ª RM', 'CML', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (8, '000513', 'AHEx', '1ª RM', 'CML', 'Vinculação direta ao CMDO / ODS', 'Rio de Janeiro/RJ', NULL, 'Administrativa', 'Vinculação direta ao CMDO', '', 'Não', '', '', 1, 99, 102);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (9, '006106', '38º BI', '1ª RM', 'CML', '1ª DE', 'Vila Velha/ES', '09.576.726/0001-41', 'Operacional', 'Vinculação direta ao CMDO', '', 'Sim', '', '', 2, 3, 8);
INSERT INTO tb_om (CODIGO, CODOM, SIGLA, RM, CMA, DE, CIDADEESTADO, CNPJ, Tipo, BDA, FORPRON, OMCOMMATERIAL, ENDERECO, CEP, NIVEL, ID, CODREGRA) VALUES (10, '015149', 'CI Op Esp', '1ª RM', 'CML', 'Vinculação direta ao CMDO / ODS', 'Niterói/RJ', '10.197.253/0001-50', 'Ensino', 'C Op Esp', 'Sim', 'Sim', '', '', 2, 99, 101);

INSERT INTO tb_turma (nome, id_treinamento) VALUES ('Turma 1', 1);
INSERT INTO tb_turma (nome, id_treinamento) VALUES ('Turma 2', 1);

INSERT INTO tb_instrutor (nome, email, contato, id_treinamento) VALUES ('Instrutor A', 'instrutorA@email.com', '+5561999120152', 1);

INSERT INTO tb_ocorrencia (titulo, id_treinamento) VALUES ('Ocorrencia 1', 1);