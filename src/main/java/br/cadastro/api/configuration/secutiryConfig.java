package br.cadastro.api.configuration;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.OncePerRequestFilter;
import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

import br.cadastro.api.manager.UsuarioManager;
import br.cadastro.api.security.JwtAuthFilter;
import br.cadastro.api.security.JwtService;

@EnableWebSecurity
@Configuration
public class secutiryConfig extends SecurityConfigurerAdapter {

	@Autowired
	private UsuarioManager usuarioManager;

	@Autowired
	private JwtService jwtService;

	@Bean
	public PasswordEncoder PasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public OncePerRequestFilter jwtFilter() {
		return new JwtAuthFilter(jwtService, usuarioManager);
	}

	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.setAllowedHeaders(Arrays.asList("Access-Control-Allow-Headers", "Access-Control-Allow-Origin",
				"Access-Control-Request-Method", "Access-Control-Request-Headers", "Origin", "Cache-Control",
				"Content-Type", "Authorization"));
		configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080"));
		configuration.setAllowedMethods(Arrays.asList("DELETE", "GET", "POST", "PATCH", "PUT"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(auth -> auth.requestMatchers(antMatcher("/gerenciamento-cargo/**"))
						.hasAnyRole("USER", "ADMIN")
						.requestMatchers(antMatcher("/gerenciamento-colaborador/**")).hasAnyRole("USER", "ADMIN")
						.requestMatchers(antMatcher("/gerenciamento-departamento/**")).hasAnyRole("USER", "ADMIN")
						.requestMatchers(antMatcher("/gerenciamento-endereco/**")).hasAnyRole("USER", "ADMIN")
						.requestMatchers(antMatcher(HttpMethod.POST, "/usuario/criar")).permitAll()
						.requestMatchers(antMatcher(HttpMethod.POST, "/usuario/alterar")).hasRole("ADMIN")
						.requestMatchers(antMatcher(HttpMethod.GET, "/usuario/tag")).hasAnyRole("USER", "ADMIN")
						.requestMatchers(antMatcher(HttpMethod.DELETE, "/usuario/deletar/**")).hasRole("ADMIN")
						.anyRequest().authenticated())
				.sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class).cors()
				.configurationSource(corsConfigurationSource());
		return http.build();
	}
}
