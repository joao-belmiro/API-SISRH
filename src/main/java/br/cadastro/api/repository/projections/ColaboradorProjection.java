package br.cadastro.api.repository.projections;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public interface ColaboradorProjection {
	Long getIdColaborador();
	Long getIdCargo ();
	Long getIdDepartamento ();
	String getNomeColaborador();
	String getCpfCnpj();
	String getEmail();
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Belem")
	Date getDataContratacao();
	
	String getNomeCargo();
	String getNomeDepartamento();
	String getTelefone();
	Double getSalario();
}
