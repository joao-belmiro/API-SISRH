package br.cadastro.api.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Cargo;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.models.Departamento;
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
	
	@Query("select c from Colaborador c where UPPER(c.nomeColaborador) like concat('%',upper(:tag),'%') "
			+ "or UPPER(c.cargo.nomeCargo) like concat('%',upper(:tag),'%')"
			+ "or UPPER(c.departamento.nomeDepartamento) like concat('%',upper(:tag),'%')")
	List<Colaborador> findByTag(String tag);
	
	List<Colaborador> findTop10By();
	
	List<Colaborador> findByDepartamento (Departamento departamento);
	
	Optional<Colaborador> findByCargo(Cargo cargo);
}
