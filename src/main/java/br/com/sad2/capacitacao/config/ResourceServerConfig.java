package br.com.sad2.capacitacao.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableResourceServer // Habilita a aplicação como um servidor de recursos para OAuth2.
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private Environment env;

	@Autowired
	private JwtTokenStore store;

	private static final String[] PUBLIC = { 
		"/oauth/token", // Endpoint para obtenção de tokens.
		"/h2-console/**", // Console do banco de dados H2.
		"/usuarios/registrar", // Registro de novos usuários.
		"/usuarios/recuperar", // Recuperação de contas.
		"/usuarios/confirmar", // Confirmação de registro.
		"/usuarios/redirecionarParaTrocarSenha", // Redirecionamento para trocar senha.
	};

	/**
     * Configura o armazenamento de tokens para o servidor de recursos.
     */
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		resources.tokenStore(store);
	}

	/**
     * Configura as regras de segurança para os endpoints protegidos.<br>
     * - Desabilita as restrições de cabeçalhos para o banco H2 quando o perfil "test" está ativo.<br>
     * - Define permissões de acesso:<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;- Endpoints públicos são acessíveis sem autenticação.<br>
     * &nbsp;&nbsp;&nbsp;&nbsp;- Qualquer outra requisição exige autenticação.<br>
     * - Configura CORS para permitir solicitações de origens específicas.<br>
     */
	@Override
	public void configure(HttpSecurity http) throws Exception {
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers(headers -> headers.frameOptions().disable());
		}

		http.authorizeRequests(requests -> requests.antMatchers(PUBLIC).permitAll().anyRequest().authenticated());

		http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
	}

	/**
     * Configura a política de CORS para a aplicação.<br>
     * - Permite origens específicas.<br>
     * - Permite métodos HTTP específicos.<br>
     * - Permite cabeçalhos específicos, incluindo "Authorization" e "Content-Type".<br>
     * - Habilita credenciais em solicitações.<br>
     */
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Arrays.asList("http://localhost:3000", "http://localhost:3056", "http://172.20.71.150:3000", "http://172.20.71.150:3056"));
		corsConfig.setAllowedMethods(Arrays.asList("POST", "GET", "PUT", "DELETE", "PATCH"));
		corsConfig.setAllowCredentials(true);
		corsConfig.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);
		return source;
	}

	/**
     * Registra o filtro de CORS para ser aplicado a todas as requisições.<br>
     * Define alta prioridade para o filtro.
     */
	@Bean
	FilterRegistrationBean<CorsFilter> corsFilter() {
		FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(
				new CorsFilter(corsConfigurationSource()));
		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return bean;
	}
}
