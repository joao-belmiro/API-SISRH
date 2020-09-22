package br.cadastro.api.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.cadastro.api.manager.UsuarioManager;
import br.cadastro.api.security.JwtAuthFilter;
import br.cadastro.api.security.JwtService;

@EnableWebSecurity
public class secutiryConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public PasswordEncoder PasswordEncoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public OncePerRequestFilter jwtFilter () {
		return new JwtAuthFilter(jwtService, usuarioManager);
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource () {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers","Access-Control-Allow-Origin","Access-Control-Request-Method", "Access-Control-Request-Headers","Origin","Cache-Control", "Content-Type", "Authorization"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
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
			.antMatchers(HttpMethod.POST,"/usuario/**").permitAll()
			.antMatchers(HttpMethod.GET, "/usuario/tag").hasRole("ADMIN")
			.antMatchers("/h2/**").permitAll()
		.and()
			.headers().frameOptions().sameOrigin()
		.and()
		 .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)		 
		.and()
		  .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
		  .cors().configurationSource(corsConfigurationSource());
		
		
	}
	
}
