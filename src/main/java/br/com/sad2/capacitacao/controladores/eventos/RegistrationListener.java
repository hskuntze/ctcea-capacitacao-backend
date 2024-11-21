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

	@Override
	public void onApplicationEvent(OnRegistrationCompleteEvent event) {
		Map<String, Object> variables = new HashMap<>();
		UsuarioDTO usuario = event.getUsuario();
		
		String token = UUID.randomUUID().toString();
		tokenServico.criarTokenVerificacao(usuario, token);
		
		String para = usuario.getEmail();
		String assunto = "Confirme o seu registro no SGC";
		String urlConfirmacao = event.getAppUrl() + "/capacitacao/api/usuarios/confirmar?token=" + token;
		
		variables.put("text", "Seja bem-vindo ao Sistema de Gerenciamento de Capacitação");
		variables.put("linkText", "Confirme seu registro");
		variables.put("urlConfirmation", urlConfirmacao);
		
		emailServico.enviarEmailHtmlComThymeleaf("email-confirmation", para, assunto, variables);
	}
}
