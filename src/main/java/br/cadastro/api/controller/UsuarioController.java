package br.cadastro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.cadastro.api.dto.CredenciaisDto;
import br.cadastro.api.dto.TokenDto;
import br.cadastro.api.dto.UsuarioDto;
import br.cadastro.api.exceptions.CredenciaisIncorretasException;
import br.cadastro.api.manager.UsuarioManager;
import br.cadastro.api.models.Usuario;
import br.cadastro.api.repository.projections.UserData;
import br.cadastro.api.security.JwtService;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	private UsuarioManager usuarioManager;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtService jwtService;
	
	@PostMapping("criar")
	public ResponseEntity<Usuario> salvarUsuario (@RequestBody Usuario usuario) {
		
		String senhaCriptografada = encoder.encode(usuario.getSenha());
		usuario.setSenha(senhaCriptografada);
		Usuario user  = usuarioManager.salvarUsuario(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
	
	public ResponseEntity<UsuarioDto> alterar (@RequestBody UsuarioDto UsuarioDto) {
		usuarioManager.alterar(UsuarioDto);
		return new ResponseEntity<UsuarioDto>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping("tag")
	public ResponseEntity<List<UserData>> filtro(@RequestParam(required = true) String tag) {
		List<UserData> users = usuarioManager.buscarPorTag(tag);
		return new ResponseEntity<List<UserData>>(users, HttpStatus.OK);
	}
	
	@PostMapping("autenticar")
	public ResponseEntity<TokenDto> autenticar (@RequestBody CredenciaisDto credenciais) {
		try {
			Usuario usuario = new  Usuario();
			usuario.setLogin(credenciais.getLogin());
			usuario.setSenha(credenciais.getSenha());
			UserDetails usuarioAutenticado = usuarioManager.autenticar(usuario);
			jwtService.gerarToken(usuario);
			
			String token = jwtService.gerarToken(usuario);
			TokenDto tokenDto = new TokenDto();
			tokenDto.setToken(token);
			tokenDto.setLogin(usuarioAutenticado.getUsername());
			return new ResponseEntity<TokenDto>(tokenDto , HttpStatus.OK);
			
		} catch (UsernameNotFoundException | CredenciaisIncorretasException e) {
			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
		}
			
	}
	
}
