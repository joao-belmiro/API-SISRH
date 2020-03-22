package br.cadastro.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{

	@Query("select c from Cargo c where c.nomeCargo like %:tag%")
	List<Cargo> findByTag(String tag);
	
}
