package br.cadastro.api.models;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CARGO")
public class Cargo implements Serializable {

	private static final long serialVersionUID = 5244528531809242755L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CARGO")
	private long idCargo;
	
	@Column(name = "NOME_CARGO", nullable = false)
	private String nomeCargo;
	
	@Column(name = "DESCRICAO_CARGO", nullable = false)
	private String descricaoCargo;
	
	@OneToMany(mappedBy = "cargo" ,targetEntity = Funcionario.class)
	private List<Funcionario> funcionario;

	public long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(long idCargo) {
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

	public List<Funcionario> getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(List<Funcionario> funcionario) {
		this.funcionario = funcionario;
	}


	
}
