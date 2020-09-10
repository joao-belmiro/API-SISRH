package br.cadastro.api.repository.projections;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.cadastro.api.models.Colaborador;

public interface DepartamentoDash {
	long getIdDepartamento();
	String getNomeDepartamento();
	String getDescricao();
	@JsonIgnore
	List<Colaborador> getColaboradores();
	int getNColaboradores();
	double getCustoDepartamento();
	Map<String, Long> getCountCargos();
}
