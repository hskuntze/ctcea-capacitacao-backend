package br.com.sad2.capacitacao.servicos;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.entities.Usuario;
import br.com.sad2.capacitacao.entities.tokens.TokenRecuperacaoSenha;
import br.com.sad2.capacitacao.entities.tokens.TokenVerificacao;
import br.com.sad2.capacitacao.repositorios.TokenRecuperacaoRepositorio;
import br.com.sad2.capacitacao.repositorios.TokenVerificacaoRepositorio;
import br.com.sad2.capacitacao.repositorios.UsuarioRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;
import br.com.sad2.capacitacao.servicos.excecoes.TokenInvalidoException;

@Service
public class TokenServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;

	@Autowired
	private TokenVerificacaoRepositorio tokenVerificacaoRepositorio;

	@Autowired
	private TokenRecuperacaoRepositorio tokenRecuperacaoRepositorio;

	@Autowired
	private EmailServico emailServico;

	@Transactional(readOnly = true)
	public Usuario buscarUsuarioPeloTokenRecuperacao(String token) {
		Usuario usuario = tokenRecuperacaoRepositorio.findUsuarioByToken(token)
				.orElseThrow(() -> new RecursoNaoEncontradoException(
						"Não foi possível encontrar usuário através do token " + token));
		
		return usuario;
	}

	@Transactional(readOnly = true)
	public TokenVerificacao buscarTokenVerificacaoPeloToken(String token) {
		Optional<TokenVerificacao> tk = tokenVerificacaoRepositorio.findByToken(token);

		if (tk.isEmpty()) {
			throw new RecursoNaoEncontradoException("O token " + token + " não foi localizado na base de dados.");
		}

		return tk.get();
	}

	@Transactional(readOnly = true)
	public TokenRecuperacaoSenha buscarTokenRecuperacaoPeloToken(String token) {
		Optional<TokenRecuperacaoSenha> tk = tokenRecuperacaoRepositorio.findByToken(token);

		if (tk.isEmpty()) {
			throw new RecursoNaoEncontradoException("O token " + token + " não foi localizado na base de dados.");
		}

		return tk.get();
	}

	@Transactional(readOnly = true)
	public void validarTokenRecuperacao(String token) {
		Optional<TokenRecuperacaoSenha> tk = tokenRecuperacaoRepositorio.findByToken(token);

		if (!tk.isEmpty()) {
			Calendar calendar = Calendar.getInstance();
			boolean expired = tk.get().getDataExpiracao().before(calendar.getTime());

			if (expired) {
				throw new TokenInvalidoException("Token expirado.");
			}
		} else {
			throw new RecursoNaoEncontradoException("Token " + token + " não foi localizado no banco de dados.");
		}
	}

	@Transactional
	public void criarTokenVerificacao(UsuarioDTO dto, String token) {
		if (dto != null && token != null) {
			Usuario usuario = usuarioRepositorio.findById(dto.getId())
					.orElseThrow(() -> new RecursoNaoEncontradoException(
							"Usuário não foi localizado na base de dados durante a operação de salvar token de verificação."));
			TokenVerificacao tk = new TokenVerificacao(usuario, token);
			tokenVerificacaoRepositorio.save(tk);
		} else {
			throw new RequisicaoNaoProcessavelException("Um ou mais argumentos estão nulos. Requisição improcessável.");
		}
	}

	@Transactional
	public void criarTokenRecuperacao(UsuarioDTO dto, String token) {
		if (dto != null && token != null) {
			Usuario usuario = usuarioRepositorio.findById(dto.getId())
					.orElseThrow(() -> new RecursoNaoEncontradoException(
							"Usuário não foi localizado na base de dados durante a operação de salvar token de recuperação."));
			TokenRecuperacaoSenha tk = new TokenRecuperacaoSenha(usuario, token);
			tokenRecuperacaoRepositorio.save(tk);
		} else {
			throw new RequisicaoNaoProcessavelException("Um ou mais argumentos estão nulos. Requisição improcessável.");
		}
	}

	@Transactional
	public void enviarToken(String email, String appUrl) {
		Map<String, Object> variaveis = new HashMap<>();

		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario != null) {
			String token = UUID.randomUUID().toString();
			criarTokenRecuperacao(new UsuarioDTO(usuario), token);

			String url = appUrl + "/capacitacao/api/usuarios/redirecionarParaTrocarSenha?token=" + token;
			String text = "Você tem 24 horas para trocar a senha.";

			variaveis.put("urlConfirmation", url);
			variaveis.put("linkText", "Clique aqui para trocar a senha");
			variaveis.put("text", text);

			emailServico.enviarEmailHtmlComThymeleaf("email-confirmation", email, "Troca de senha", variaveis);
		} else {
			throw new RecursoNaoEncontradoException("Não foi possível localizar usuário com email " + email);
		}
	}

	@Transactional
	public boolean deletarTokenVerificacao(String token) {
		int rows = tokenVerificacaoRepositorio.excluirTokenVerificacaoBaseadoNoToken(token);
		if (rows == 0) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean deletarTokenVerificacao(Long id) {
		int rows = tokenVerificacaoRepositorio.excluirTokenVerificacaoBaseadoNoUsuario(id);
		if (rows == 0) {
			return false;
		}
		return true;
	}

	@Transactional
	public boolean deletarTokenRecuperacao(String token) {
		int rows = tokenRecuperacaoRepositorio.excluirTokenRecuperacaoBaseadoNoToken(token);
		if (rows == 0) {
			return false;
		}
		return true;
	}
	
	@Transactional
	public boolean deletarTokenRecuperacao(Long id) {
		int rows = tokenRecuperacaoRepositorio.excluirTokenRecuperacaoBaseadoNoUsuario(id);
		if (rows == 0) {
			return false;
		}
		return true;
	}
}
