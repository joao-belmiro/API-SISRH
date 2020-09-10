package br.cadastro.api.repository.projections;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import br.cadastro.api.models.Colaborador;

public interface CargoDashProjection {
	String getNomeCargo();
	@JsonIgnore
	List<Colaborador> getColaboradores();
	int getNCargos();
}
