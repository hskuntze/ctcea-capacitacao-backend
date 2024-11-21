package br.com.sad2.capacitacao.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.entities.Usuario;
import br.com.sad2.capacitacao.entities.tokens.TokenVerificacao;
import br.com.sad2.capacitacao.repositorios.TokenVerificacaoRepositorio;
import br.com.sad2.capacitacao.repositorios.UsuarioRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;

@Service
public class TokenServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private TokenVerificacaoRepositorio tokenVerificacaoRepositorio;
	
	@Transactional(readOnly = true)
	public TokenVerificacao buscarTokenVerificacaoPeloToken(String token) {
		Optional<TokenVerificacao> tk = tokenVerificacaoRepositorio.findByToken(token);
		
		if(tk.isEmpty()) {
			throw new RecursoNaoEncontradoException("O token " + token + " não foi localizado na base de dados.");
		}
		
		return tk.get();
	}
	
	@Transactional
	public void criarTokenVerificacao(UsuarioDTO dto, String token) {
		if(dto != null && token != null) {
			Usuario usuario = usuarioRepositorio.findById(dto.getId()).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário não foi localizado na base de dados durante a operação de salvar token de verificação."));
			TokenVerificacao tk = new TokenVerificacao(usuario, token);
			tokenVerificacaoRepositorio.save(tk);	
		} else {
			throw new RequisicaoNaoProcessavelException("Um ou mais argumentos estão nulos. Requisição improcessável.");
		}
	}
	
	@Transactional
	public boolean deletarTokenVerificacao(String token) {
		int rows = tokenVerificacaoRepositorio.excluirTokenVerificacaoBaseadoNoToken(token);
		if(rows == 0) {
			return false;
		}
		return true;
	}
}
