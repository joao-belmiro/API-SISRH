package br.cadastro.api.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.cadastro.api.models.Usuario;
import br.cadastro.api.repository.UsuarioRepository;

@Service
public class UsuarioManager implements UserDetailsService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
		
	@Transactional
	public Usuario salvarUsuario (Usuario user) {
		return usuarioRepository.save(user);
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
	
		Usuario user = usuarioRepository.findByLogin(login).orElseThrow(() ->  new UsernameNotFoundException("Usuário não cadastrado na base de dados"));
	   
		String[] roles = user.getAdmin() ? new String[] {"USER" ,"ADMIN"} : new String[] {"USER"};
		
		return User.builder()
				.username(user.getLogin())
				.password(user.getSenha())
				.roles(roles)
				.build();
	}

	
}
