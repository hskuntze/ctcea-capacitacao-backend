package br.com.sad2.capacitacao.entities.tokens;

import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.sad2.capacitacao.entities.Usuario;

@Entity
@Table(name = "tb_token_verificacao")
public class TokenVerificacao extends TokenAbstrato {

	public TokenVerificacao() {
	}
	
	public TokenVerificacao(Usuario usuario, String token) {
		this.setToken(token);
		this.setUsuario(usuario);
		this.setDataExpiracao(this.calcularDataExpiracao(getExpiracao()));
	}
}
