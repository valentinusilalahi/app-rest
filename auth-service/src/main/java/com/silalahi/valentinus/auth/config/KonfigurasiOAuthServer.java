package com.silalahi.valentinus.auth.config;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

@Configuration
@EnableAuthorizationServer
public class KonfigurasiOAuthServer extends AuthorizationServerConfigurerAdapter {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private AuthenticationManager authenticationManager;

	@Value("${keystore.file}")
	private String keystoreFile;
	@Value("${keystore.alias}")
	private String keystoreAlias;
	@Value("${keystore.password}")
	private String keystorePassword;

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		super.configure(endpoints);
		endpoints.tokenStore(new JdbcTokenStore(dataSource)).authenticationManager(authenticationManager)
				.accessTokenConverter(jwtAccessTokenConverter());

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		super.configure(security);
		security.checkTokenAccess("isAuthenticated()").tokenKeyAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		// TODO Auto-generated method stub
		super.configure(clients);
		clients.jdbc(dataSource);
	}

	public JwtAccessTokenConverter jwtAccessTokenConverter() {
		JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
		KeyPair key = new KeyStoreKeyFactory(new ClassPathResource(keystoreFile), keystorePassword.toCharArray())
				.getKeyPair(keystoreAlias);
		converter.setKeyPair(key);
		return converter;
	}

}
