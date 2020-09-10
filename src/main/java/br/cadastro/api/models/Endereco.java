package br.cadastro.api.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name= "ENDERECO")
public class Endereco implements Serializable {
	private static final long serialVersionUID = 8125844071176465703L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="ID_ENDERECO")
	private long idEndereco;
	
	@Column(name ="CEP", nullable = false)
	private String cep;
	
	@Column(name = "NUMERO", nullable = false)
	private String numero;
	
	@Column(name = "COMPLEMENTO", nullable = false)	
	private String complemento;
	
	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ID_FUNCIONARIO_FK")
	private Colaborador colaborador;
	
	public long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

		
}
