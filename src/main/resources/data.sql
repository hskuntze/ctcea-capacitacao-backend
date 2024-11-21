INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_ADMIN');
INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_USUARIO');

INSERT INTO tb_usuario (nome, sobrenome, email, senha, habilitado, registro_completo) VALUES ('Hassan', 'Kuntze', 'hassankrc@ctcea.org.br', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, true);

INSERT INTO tb_perfil_usuario (id_usuario, id_perfil) VALUES (1, 1);

INSERT INTO tb_treinamento (
  sad, material, treinamento, tipo, subsistema, modalidade, 
  brigada, om, grupo, executor, instituicao, data_inicio, 
  data_fim, vagas, status, avaliacao_pratica, avaliacao_teorica, 
  nome_instrutores, contato_instrutores, certificado, logistica_treinamento, 
  nivelamento, carga_horaria, publico_alvo, descricao_atividade, 
  material_didatico, observacoes, pre_requisitos
)
VALUES (
  'SAD2', 'Material A', 'Treinamento de Primeiros Socorros', 3, 'Sistema de Saúde', 1, 
  'Brigada 1', 'OM001', 1, 2, 'Instituição A', '2024-01-10', 
  '2024-01-15', 50, 2, 1, 1, 
  'Instrutor A', 'contato@instrutora.com', 1, 'Logística do treinamento XYZ', 
  1, 40, 1, 'Treinamento sobre primeiros socorros com simulações práticas', 
  'Material didático: slides e apostilas', 'Nenhuma observação adicional', 'Pré-requisito: Noções básicas de saúde'
);