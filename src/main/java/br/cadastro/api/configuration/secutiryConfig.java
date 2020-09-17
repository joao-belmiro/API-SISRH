package br.cadastro.api.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.cadastro.api.manager.UsuarioManager;

@EnableWebSecurity
public class secutiryConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@Bean
	public PasswordEncoder PasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.userDetailsService(usuarioManager)
		.passwordEncoder(PasswordEncoder());
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
			.antMatchers("/gerenciamento-cargo/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/gerenciamento-colaborador/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/gerenciamento-departamento/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/gerenciamento-endereco/**").hasAnyRole("USER", "ADMIN")
			.antMatchers("/usuario/**").permitAll()
		.and()
			.httpBasic()
		.and().cors().disable();
			
	}
}
