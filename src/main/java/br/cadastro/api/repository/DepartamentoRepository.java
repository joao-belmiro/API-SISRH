package br.cadastro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Departamento;

@Repository
public interface DepartamentoRepository  extends JpaRepository<Departamento, Long>{

	@Query("select d from Departamento d where d.nomeDepartamento like %:tag%")
	List<Departamento> findByTag(String tag);
}
