package br.cadastro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Cargo;
import br.cadastro.api.repository.projections.CargoDashProjection;
import br.cadastro.api.repository.projections.CargoProjection;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

	@Query("select c from Cargo c where UPPER(c.nomeCargo) like concat('%',upper(:tag),'%')")
	List<CargoProjection> findByTag(String tag);
	
	List<CargoDashProjection> findAllBy();
	
}
