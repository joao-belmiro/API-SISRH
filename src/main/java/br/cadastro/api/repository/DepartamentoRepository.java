package br.cadastro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.projections.DepartamentoDash;
import br.cadastro.api.repository.projections.DepartamentoProjection;

@Repository
public interface DepartamentoRepository  extends JpaRepository<Departamento, Long>{

	@Query("select d from Departamento d where UPPER(d.nomeDepartamento) like concat('%',upper(:tag),'%')")
	List<DepartamentoProjection> findByTag(String tag);
	
	List<DepartamentoDash> findAllBy();
}
