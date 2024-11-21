INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_ADMIN');
INSERT INTO tb_perfil (autorizacao) VALUES ('PERFIL_USUARIO');

INSERT INTO tb_usuario (nome, sobrenome, email, senha, habilitado, registro_completo) VALUES ('Hassan', 'Kuntze', 'hassankrc@ctcea.org.br', '$2a$10$eACCYoNOHEqXve8aIWT8Nu3PkMXWBaOxJ9aORUYzfMQCbVBIhZ8tG', true, true);

INSERT INTO tb_perfil_usuario (id_usuario, id_perfil) VALUES (1, 1);