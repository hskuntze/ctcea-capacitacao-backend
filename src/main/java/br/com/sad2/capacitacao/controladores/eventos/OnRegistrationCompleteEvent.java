package br.com.sad2.capacitacao.controladores.eventos;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import br.com.sad2.capacitacao.dto.UsuarioDTO;

public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -6893229898639725354L;
	
	private UsuarioDTO usuario;
	private Locale locale;
	private String appUrl;
	
	public OnRegistrationCompleteEvent(UsuarioDTO usuario, Locale locale, String appUrl) {
		super(usuario);
		this.usuario = usuario;
		this.locale = locale;
		this.appUrl = appUrl;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}
}