package br.cadastro.api.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Funcionario;
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	
	@Query("select f from Funcionario f where f.nomeFuncionario like %:tag%")
	List<Funcionario> findByTag(String tag);

}
