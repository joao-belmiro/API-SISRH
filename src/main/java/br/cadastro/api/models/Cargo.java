package br.cadastro.api.models;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CARGO")
public class Cargo implements Serializable {

	private static final long serialVersionUID = 5244528531809242755L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_CARGO")
	private Long idCargo;

	@Column(name = "NOME_CARGO", nullable = false)
	@NotBlank(message = "O nome do Cargo não pode ser espaço em vazio")
	@NotNull(message = "Nome do Cargo não pode ser nulo")
	private String nomeCargo;

	@NotBlank(message = "Descrição não pode ser espaço em vazio")
	@NotNull(message = "Descrição não pode ser nula")
	@Column(name = "DESCRICAO_CARGO", nullable = false)
	private String descricaoCargo;

	@JsonBackReference
	@OneToMany(mappedBy = "cargo", fetch = FetchType.LAZY)
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
	public int getNCargos() {
		return colaboradores.size();
	}

}
