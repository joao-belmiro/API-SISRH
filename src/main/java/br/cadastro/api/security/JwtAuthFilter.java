package br.cadastro.api.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import br.cadastro.api.manager.UsuarioManager;

public class JwtAuthFilter extends OncePerRequestFilter {

	private JwtService jwtService;
	private UsuarioManager usuarioManager;

	public JwtAuthFilter(JwtService jwtService, UsuarioManager usuarioManager) {
		this.jwtService = jwtService;
		this.usuarioManager = usuarioManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String auth = request.getHeader("Authorization");
		if (auth != null && auth.startsWith("Bearer")) {
			String token = auth.split(" ")[1];
			Boolean valido = jwtService.tokenValido(token);
			if (valido) {
				String login = jwtService.obterLogin(token);
				UserDetails usuario = usuarioManager.loadUserByUsername(login);
				UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(usuario, null,
						usuario.getAuthorities());
				user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(user);
			}
		}
		filterChain.doFilter(request, response);

	}

}
