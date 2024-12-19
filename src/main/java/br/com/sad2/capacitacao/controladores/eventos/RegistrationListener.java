package br.com.sad2.capacitacao.controladores.eventos;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import br.com.sad2.capacitacao.dto.UsuarioDTO;
import br.com.sad2.capacitacao.servicos.EmailServico;
import br.com.sad2.capacitacao.servicos.TokenServico;

@Component
public class RegistrationListener implements ApplicationListener<OnRegistrationCompleteEvent>{

	@Autowired
	private TokenServico tokenServico;
	
	@Autowired
	private EmailServico emailServico;

	 /**
     * Método executado automaticamente quando um evento `OnRegistrationCompleteEvent` é publicado.<br>
     * Este evento é usado para lidar com a lógica de registro de novos usuários.
     */
	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		// Variáveis usadas para preencher o template do e-mail.
		Map<String, Object> variables = new HashMap<>();
		UsuarioDTO usuario = event.getUsuario();
		
		String token = UUID.randomUUID().toString();
		tokenServico.criarTokenVerificacao(usuario, token);
		
		String para = usuario.getEmail();
		String assunto = "Confirme o seu registro no SGC";
		
		// Gera a URL de confirmação usando a URL base da aplicação e o token gerado.
		String urlConfirmacao = event.getAppUrl() + "/capacitacao/api/usuarios/confirmar?token=" + token;
		
		variables.put("text", "Seja bem-vindo ao Sistema de Gerenciamento de Capacitação");
		variables.put("linkText", "Confirme seu registro");
		variables.put("urlConfirmation", urlConfirmacao);
		
		// Envia um e-mail HTML usando o serviço de e-mail e o template Thymeleaf "email-confirmation".
		emailServico.enviarEmailHtmlComThymeleaf("email-confirmation", para, assunto, variables);
	}
}
