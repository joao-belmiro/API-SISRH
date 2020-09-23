package br.cadastro.api.manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.cadastro.api.dto.UsuarioDto;
import br.cadastro.api.exceptions.CredenciaisIncorretasException;
import br.cadastro.api.exceptions.UsuarioLogadoException;
import br.cadastro.api.models.Usuario;
import br.cadastro.api.repository.UsuarioRepository;
import br.cadastro.api.repository.projections.UserData;
import br.cadastro.api.security.JwtService;

@Service
public class UsuarioManager implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtService jwtService;

	@Transactional
	public Usuario salvarUsuario(Usuario user) throws UsernameNotFoundException, CredenciaisIncorretasException {
		return usuarioRepository.save(user);
	}

	public void alterar(UsuarioDto usuarioDto) {
		Usuario usuarioEncontrado = usuarioRepository.findById(usuarioDto.getId())
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado na base de dados"));
		Boolean senhasBatem = encoder.matches(usuarioDto.getSenha(), usuarioEncontrado.getSenha());
		if (senhasBatem) {
			Usuario usuario = new Usuario();
			usuario.setId(usuarioDto.getId());
			usuario.setLogin(usuarioDto.getLogin());
			usuario.setAdmin(usuarioDto.getAdmin());
			String novaSenha = encoder.encode(usuarioDto.getNovaSenha());
			usuario.setSenha(novaSenha);
			usuarioRepository.save(usuario);

		} else {
			throw new CredenciaisIncorretasException();
		}
	}

	public void deletar(String token, Long id) throws UsernameNotFoundException, UsuarioLogadoException {
		String tokenFormatado = token.split(" ")[1];
		String userName = jwtService.obterLogin(tokenFormatado);
		Usuario usuarioToken = usuarioRepository.findByLogin(userName)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado na base de dados"));
		Usuario usuarioExcluir = usuarioRepository.findById(id).orElse(null);
		if (usuarioToken.equals(usuarioExcluir)) {
			throw new UsuarioLogadoException("Não é possivel excluír um usuário com sessão aberta");
		} else {
			usuarioRepository.delete(usuarioExcluir);
			;
		}
	}

	public List<UserData> buscarPorTag(String tag) {
		return usuarioRepository.buscarPorTag(tag);
	}

	public UserDetails autenticar(Usuario usuario) {
		UserDetails user = loadUserByUsername(usuario.getLogin());
		Boolean valido = encoder.matches(usuario.getSenha(), user.getPassword());
		if (valido) {
			return user;
		}
		throw new CredenciaisIncorretasException();
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		Usuario user = usuarioRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("Usuário não cadastrado na base de dados"));

		String[] roles = user.getAdmin() ? new String[] { "USER", "ADMIN" } : new String[] { "USER" };

		return User.builder().username(user.getLogin()).password(user.getSenha()).roles(roles).build();
	}

}
