package br.com.sad2.capacitacao.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.Usuario;
import br.com.sad2.capacitacao.entities.tokens.TokenRecuperacaoSenha;

@Repository
public interface TokenRecuperacaoRepositorio extends JpaRepository<TokenRecuperacaoSenha, Long> {
	Optional<TokenRecuperacaoSenha> findByToken(String token);
	
	@Query("SELECT u FROM Usuario u JOIN TokenRecuperacaoSenha t ON u = t.usuario WHERE t.token = :token")
	Optional<Usuario> findUsuarioByToken(String token);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_token_recuperacao ttr "
			+ "WHERE ttr.token = :token")
	int excluirTokenRecuperacaoBaseadoNoToken(String token);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_token_recuperacao ttr "
			+ "WHERE ttr.user_id = :id")
	int excluirTokenRecuperacaoBaseadoNoUsuario(Long id);
}
