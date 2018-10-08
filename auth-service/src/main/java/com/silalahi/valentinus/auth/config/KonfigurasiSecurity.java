package com.silalahi.valentinus.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class KonfigurasiSecurity extends WebSecurityConfigurerAdapter {
	private static final String SQL_LOGIN = "select u.username, p.password, u.active"
			+ "from s_user u inner join s_user_password p " + "on u.id = p.id_user " + "where u.username = ?";

	private static final String SQL_PERMISION = "select u.username, p.permission_value as authority "
			+ "from s_user u inner join s_role r on u.id_role = r.id "
			+ "inner join s_role_permission rp on r.id = rp.id_role "
			+ "inner join s_permission p on rp.id_permission = p.id " + "where u.username=?";

	@Autowired
	private DataSource dataSource;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource).usersByUsernameQuery(SQL_LOGIN)
				.authoritiesByUsernameQuery(SQL_PERMISION).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		super.configure(http);
		http.authorizeRequests().anyRequest().authenticated().and().formLogin();
	}

	// export / publish AuthenticationManager untuk digunakan di konfig OAuth
	@Override
	@Bean
	public AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManagerBean();
	}

}
