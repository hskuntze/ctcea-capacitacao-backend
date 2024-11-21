package br.com.sad2.capacitacao.servicos.excecoes;

public class TokenInvalido extends RuntimeException {
	private static final long serialVersionUID = -4626073001041043059L;
	
	public TokenInvalido() {
	}

	public TokenInvalido(String msg) {
		super(msg);
	}
	
	public TokenInvalido(String msg, Throwable cause) {
		super(msg, cause);
	}
}
