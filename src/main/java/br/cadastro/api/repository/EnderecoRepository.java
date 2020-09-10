package br.cadastro.api.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.cadastro.api.models.Endereco;
import br.cadastro.api.repository.projections.EnderecoProjetction;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long> {
	
	Optional<EnderecoProjetction> findByIdEndereco(Long idEndereco);
}
