package br.cadastro.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="CARGO")
public class Cargo implements Serializable {

	private static final long serialVersionUID = 5244528531809242755L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CARGO")
	private Long idCargo;
	
	@Column(name = "NOME_CARGO", nullable = false)
	@NotNull(message = "Nome do Cargo não pode ser nulo")
	private String nomeCargo;
	
	@Column(name = "DESCRICAO_CARGO", nullable = false)
	private String descricaoCargo;
	
	@JsonBackReference
	@OneToMany(mappedBy = "cargo",fetch = FetchType.LAZY)
	private List<Colaborador> colaboradores;

	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	public String getNomeCargo() {
		return nomeCargo;
	}

	public void setNomeCargo(String nomeCargo) {
		this.nomeCargo = nomeCargo;
	}

	public String getDescricaoCargo() {
		return descricaoCargo;
	}

	public void setDescricaoCargo(String descricaoCargo) {
		this.descricaoCargo = descricaoCargo;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaborador) {
		this.colaboradores = colaborador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	@JsonIgnore
	public int getNCargos () {
		return colaboradores.size();
	}


}
