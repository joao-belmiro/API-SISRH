package br.cadastro.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.cadastro.api.manager.UsuarioManager;
import br.cadastro.api.models.Usuario;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
	
	@Autowired
	UsuarioManager usuarioManager;
	
	@PostMapping("criar")
	public ResponseEntity<Usuario> salvarUsuario (@RequestBody Usuario usuario) {
		Usuario user  = usuarioManager.salvarUsuario(usuario);
		
		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);
	}
}
