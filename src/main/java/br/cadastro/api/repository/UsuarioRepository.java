package br.cadastro.api.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.cadastro.api.models.Usuario;
import br.cadastro.api.repository.projections.UserData;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
	
	@Query("select u from Usuario u where UPPER(u.login) like concat('%',upper(:tag),'%')")
	List<UserData> buscarPorTag(String tag);
}
