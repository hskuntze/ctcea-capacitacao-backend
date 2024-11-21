package br.com.sad2.capacitacao.servicos;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.dto.UsuarioRegistroDTO;
import br.com.sad2.capacitacao.entities.Perfil;
import br.com.sad2.capacitacao.entities.Usuario;
import br.com.sad2.capacitacao.entities.tokens.TokenVerificacao;
import br.com.sad2.capacitacao.repositorios.PerfilRepositorio;
import br.com.sad2.capacitacao.repositorios.UsuarioRepositorio;
import br.com.sad2.capacitacao.servicos.excecoes.ConflitoException;
import br.com.sad2.capacitacao.servicos.excecoes.ErroProcessoException;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoExistenteException;
import br.com.sad2.capacitacao.servicos.excecoes.RecursoNaoEncontradoException;
import br.com.sad2.capacitacao.servicos.excecoes.RequisicaoNaoProcessavelException;
import br.com.sad2.capacitacao.servicos.excecoes.TokenInvalido;

@Service
public class UsuarioServico implements UserDetailsService {
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	@Autowired
	private PerfilRepositorio perfilRepositorio;
	
	@Autowired
	private TokenServico tokenServico;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	/**
	 * Busca um usuário pelo ID
	 * @param id
	 * @return UsuarioDTO
	 */
	@Transactional(readOnly = true)
	public UsuarioDTO buscarPorId(Long id) {
		Usuario usuario = usuarioRepositorio.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Usuário com ID " + id + " não foi encontrado"));
		return new UsuarioDTO(usuario);
	}
	
	/**
	 * Busca um usuário pelo email
	 * @param email
	 * @return UsuarioDTO
	 */
	@Transactional(readOnly = true)
	public UsuarioDTO buscarPorEmail(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if(usuario == null) {
			throw new RecursoNaoEncontradoException("Usuário com email " + email + " não foi encontrado.");
		}
		return new UsuarioDTO(usuario);
	}
	
	/**
	 * Registra um usuário na aplicação
	 * @param dto
	 * @return UsuarioDTO
	 */
	@Transactional
	public UsuarioDTO registrar(UsuarioRegistroDTO dto) {
		if(dto != null) {
			Usuario aux = usuarioRepositorio.findByEmail(dto.getEmail());
			
			if(aux == null) {
				Usuario usuario = new Usuario();
				
				dtoParaEntidade(usuario, dto);
				usuario.setHabilitado(false);
				usuario.setRegistroCompleto(false);
				usuario.setSenha(passwordEncoder.encode(dto.getPassword()));
				usuario = usuarioRepositorio.save(usuario);
				
				return new UsuarioDTO(usuario);
			} else {
				throw new RecursoExistenteException("Já existe um usuário com este email.");
			}
		} else {
			throw new RequisicaoNaoProcessavelException("Argumento nulo. Requisição improcessável.");
		}
	}
	
	/**
	 * Função para habilitar um usuário depois que for registrado
	 * @param token
	 */
	@Transactional
	public void habilitarUsuarioRegistrado(String token) {
		TokenVerificacao tokenVerificacao = tokenServico.buscarTokenVerificacaoPeloToken(token);
		
		Usuario usuario = tokenVerificacao.getUsuario();
		Calendar calendar = Calendar.getInstance();
		
		if((tokenVerificacao.getDataExpiracao().getTime() - calendar.getTime().getTime()) <= 0) {
			throw new TokenInvalido("Token expirado.");
		}
		
		if(usuario != null) {
			boolean enabled = usuario.isEnabled();
			
			if(enabled) {
				throw new ConflitoException("Este usuário já está habilitado.");
			}
			
			usuario.setHabilitado(true);
			usuarioRepositorio.save(usuario);
			
			boolean tokenExcluido = tokenServico.deletarTokenVerificacao(token);
			if(!tokenExcluido) {
				throw new ErroProcessoException("Erro durante o processo de exclusão do token de verificação.");
			}
		} else {
			throw new RequisicaoNaoProcessavelException("Usuário nulo durante o processo de habilitar o usuário.");
		}
	}

	/**
	 * Função padrão da interface "UserDetailsService" que carrega um usuário pelo 'username' dele,
	 * neste caso é o email do usuário.
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepositorio.findByEmail(username);
		if(usuario == null) {
			throw new RecursoNaoEncontradoException("Usuario " + username + " não foi encontrado.");
		}
		
		return usuario;
	}

	/**
	 * Função auxiliar para passar os dados de um DTO para a entidade concreta
	 * @param usuario
	 * @param dto
	 */
	private void dtoParaEntidade(Usuario usuario, UsuarioDTO dto) {
		usuario.setNome(dto.getNome());
		usuario.setSobrenome(dto.getSobrenome());
		usuario.setEmail(dto.getEmail());
		
		usuario.getPerfis().clear();
		dto.getPerfis().forEach(perfil -> {
			Perfil p = perfilRepositorio.getReferenceById(perfil.getId());
			usuario.getPerfis().add(p);
		});
	}
}
