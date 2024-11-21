package br.com.sad2.capacitacao.servicos.excecoes;

public class FormatacaoException extends RuntimeException {
	private static final long serialVersionUID = 1323672888116808870L;
	
	public FormatacaoException() {
	}

	public FormatacaoException(String msg) {
		super(msg);
	}
	
	public FormatacaoException(String msg, Throwable cause) {
		super(msg, cause);
	}
}
