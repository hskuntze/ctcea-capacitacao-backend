package br.com.sad2.capacitacao.controladores.eventos;

import java.util.Locale;

import org.springframework.context.ApplicationEvent;

import br.com.sad2.capacitacao.dto.UsuarioDTO;

/**
 * Evento personalizado para notificar a conclusão do registro de um usuário.<br>
 * Este evento carrega informações necessárias para lidar com a lógica
 * de confirmação de registro, como o usuário, o idioma e a URL da aplicação.
 */
public class OnRegistrationCompleteEvent extends ApplicationEvent {
	private static final long serialVersionUID = -6893229898639725354L;
	
	private UsuarioDTO usuario;
	private Locale locale;
	private String appUrl;
	
	/**
     * Construtor da classe `OnRegistrationCompleteEvent`.
     *
     * @param usuario o usuário que acabou de se registrar.
     * @param locale o idioma/localização associado ao registro do usuário.
     * @param appUrl a URL base da aplicação.
     */
	public OnRegistrationCompleteEvent(UsuarioDTO usuario, Locale locale, String appUrl) {
		super(usuario); // Chama o construtor da superclasse com o usuário como fonte do evento.
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