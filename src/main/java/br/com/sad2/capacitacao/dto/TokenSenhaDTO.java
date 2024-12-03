package br.com.sad2.capacitacao.dto;

import java.io.Serializable;

public class TokenSenhaDTO implements Serializable {
	private static final long serialVersionUID = 8695695126198496146L;

	private String token;
	private String password;
	
	public TokenSenhaDTO() {
	}
	
	public TokenSenhaDTO(String token, String password) {
		this.token = token;
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}