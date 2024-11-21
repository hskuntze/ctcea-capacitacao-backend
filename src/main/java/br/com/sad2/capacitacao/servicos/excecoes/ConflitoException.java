package br.com.sad2.capacitacao.servicos.excecoes;

public class ConflitoException extends RuntimeException {
	private static final long serialVersionUID = -7303076123082173031L;
	
	public ConflitoException() {
	}
	
	public ConflitoException(String msg) {
		super(msg);
	}
	
	public ConflitoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
