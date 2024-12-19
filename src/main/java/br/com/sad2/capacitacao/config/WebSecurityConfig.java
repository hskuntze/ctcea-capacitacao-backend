package br.com.sad2.capacitacao.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;

@Configuration
@EnableWebSecurity // Ativa a configuração de segurança da aplicação usando Spring Security.
public class WebSecurityConfig {
	
	/**
     * Configura endpoints que devem ser ignorados pelo Spring Security.<br>
     * - Neste caso, todos os endpoints que correspondem ao padrão "/actuator/**" são ignorados,<br>
     *   permitindo acesso sem qualquer autenticação.<br>
     */
	@Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().antMatchers("/actuator/**");
    }
	
	/**
     * Configura o gerenciador de autenticação da aplicação.<br>
     * - Obtém o `AuthenticationManager` a partir da configuração fornecida por `AuthenticationConfiguration`.<br>
     * - Isso permite que o gerenciador seja usado em outras partes da aplicação onde é necessário.<br>
     */
	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}
}