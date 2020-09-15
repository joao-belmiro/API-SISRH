package br.cadastro.api.repository;


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.cadastro.api.models.Cargo;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.projections.ColaboradorProjection;
@Repository
public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
	
	@Query("select c from Colaborador c where UPPER(c.nomeColaborador) like concat('%',upper(:tag),'%') "
			+ "or UPPER(c.cargo.nomeCargo) like concat('%',upper(:tag),'%')"
			+ "or UPPER(c.departamento.nomeDepartamento) like concat('%',upper(:tag),'%')")
	List<ColaboradorProjection> findByTag(String tag);
	
	List<Colaborador> findTop10By();
	
	List<Colaborador> findByDepartamento (Departamento departamento);
	
	List<Colaborador> findByCargo(Cargo cargo);
}
