package br.com.sad2.capacitacao.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableAuthorizationServer // Habilita a funcionalidade de servidor de autorização para OAuth2 no Spring.
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
	
	@Value("${security.oauth2.client.client-id}")
	private String clientId;
	
	@Value("${security.oauth2.client.client-secret}")
	private String clientSecret;
	
	@Value("${jwt.duration}")
	private Integer duration;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private JwtAccessTokenConverter converter;
	
	@Autowired
	private JwtTokenStore tokenStore;
	
	@Autowired
	private AuthenticationManager authManager;
	
	/**
     * Configura as permissões de segurança do servidor de autorização.<br>
     * - `tokenKeyAccess("permitAll()")`: Permite acesso público ao endpoint para obter a chave pública do token.<br>
     * - `checkTokenAccess("isAuthenticated()")`: Restringe a verificação de tokens a usuários autenticados.
     * <p>
     * @param security configuração de segurança do servidor de autorização.
     */
	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security.tokenKeyAccess("permitAll()").checkTokenAccess("isAuthenticated()");
	}

	/**
     * Configura os detalhes do cliente OAuth2.<br>
     * - Usa um cliente armazenado na memória.<br>
     * - Define o ID e o segredo do cliente.<br>
     * - Define os escopos permitidos ("read" e "write").<br>
     * - Habilita o fluxo de concessão de senha ("password").<br>
     * - Define a validade do token de acesso.<br>
     * <p>
     * @param clients configurador para clientes OAuth2.
     */
	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.inMemory()
			.withClient(clientId)
			.secret(encoder.encode(clientSecret))
			.scopes("read","write")
			.authorizedGrantTypes("password")
			.accessTokenValiditySeconds(duration);
	}

	/**
     * Configura os endpoints do servidor de autorização.<br>
     * - Define o gerenciador de autenticação para validar credenciais de usuário.<br>
     * - Configura o armazenamento de tokens.<br>
     * - Configura o conversor de tokens JWT.<br>
     * <p>
     * @param endpoints configurador dos endpoints do servidor de autorização.
     */
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints.authenticationManager(authManager)
			.tokenStore(tokenStore)
			.accessTokenConverter(converter);
	}
}
