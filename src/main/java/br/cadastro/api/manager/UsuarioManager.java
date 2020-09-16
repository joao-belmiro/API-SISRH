package br.cadastro.api.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Usuario;
import br.cadastro.api.repository.UsuarioRepository;

@Service
public class UsuarioManager {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	public Usuario salvarUsuario (Usuario user) {
		return usuarioRepository.save(user);
	}

	
}
