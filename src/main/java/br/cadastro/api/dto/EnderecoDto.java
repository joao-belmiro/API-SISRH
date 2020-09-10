package br.cadastro.api.dto;

import br.cadastro.api.models.Colaborador;

public class EnderecoDto {
	
	private long idEndereco; 
	
	private String cep;
	
	private String numero;
	
	private String complemento;
	
	private Colaborador colaborador;
	
	public EnderecoDto(String cep, String numero, String complemento, Colaborador colaborador) {
		super();
		this.cep = cep;
		this.numero = numero;
		this.complemento = complemento;
		this.colaborador = colaborador;
	}
	
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
	
}
