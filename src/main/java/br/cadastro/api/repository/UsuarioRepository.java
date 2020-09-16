package br.cadastro.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.cadastro.api.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String login);
}
