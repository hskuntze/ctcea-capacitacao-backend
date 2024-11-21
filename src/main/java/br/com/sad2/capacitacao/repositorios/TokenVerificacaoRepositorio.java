package br.com.sad2.capacitacao.repositorios;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.sad2.capacitacao.entities.tokens.TokenVerificacao;

@Repository
public interface TokenVerificacaoRepositorio extends JpaRepository<TokenVerificacao, Long>{
	
	Optional<TokenVerificacao> findByToken(String token);
	
	@Modifying
	@Query(nativeQuery = true, value = "DELETE FROM tb_token_verificacao ttv "
			+ "WHERE ttv.token = :token")
	int excluirTokenVerificacaoBaseadoNoToken(String token);
}
