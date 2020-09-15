package br.cadastro.api.repository.projections;

import java.sql.Date;

public interface ColaboradorProjection {
	Long getIdColaborador();
	String getNomeColaborador();
	String getCpfCnpj();
	String getEmail();
	Date getDataContratacao();
	String getNomeCargo();
	String getNomeDepartamento();
	String getTelefone();
}
