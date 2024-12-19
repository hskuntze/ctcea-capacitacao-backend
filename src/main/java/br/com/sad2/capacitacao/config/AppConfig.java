package br.com.sad2.capacitacao.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
public class AppConfig {
	
	@Value("${jwt.secret}")
	private String secret;
	
	/**
	 * Define um bean para o codificador de senhas usando o algoritmo BCrypt.
	 */
	@Bean
	BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	/**
	 * Define um bean para o conversor de tokens JWT.
     * Esse conversor é usado para codificar e decodificar os tokens JWT.
	 */
	@Bean
	JwtAccessTokenConverter converter() {
		JwtAccessTokenConverter tc = new JwtAccessTokenConverter();
		tc.setSigningKey(secret);
		return tc;
	}
	
	
	/**
	 * Define um bean para armazenar tokens JWT.
	 * O JwtTokenStore usa o conversor configurado para lidar com a serialização e validação dos tokens.
	 */
	@Bean
	JwtTokenStore store() {
		return new JwtTokenStore(converter());
	}
}
