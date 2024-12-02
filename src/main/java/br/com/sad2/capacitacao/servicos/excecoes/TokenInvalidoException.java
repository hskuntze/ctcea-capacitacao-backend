package br.com.sad2.capacitacao.servicos.excecoes;

public class TokenInvalidoException extends RuntimeException {
	private static final long serialVersionUID = -4626073001041043059L;
	
	public TokenInvalidoException() {
	}

	public TokenInvalidoException(String msg) {
		super(msg);
	}
	
	public TokenInvalidoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
