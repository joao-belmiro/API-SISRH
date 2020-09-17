package br.cadastro.api.models;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table(name = "COLABORADOR")
public class Colaborador  implements Serializable {

	private static final long serialVersionUID = 5240480156666113067L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name ="ID_COLABORADOR")
	private long idColaborador;
	
	@NotNull(message="Nome do Colaborador não pode Ser Nulo")
	@Column(name = "NOME_COLABORADOR",nullable = false)
	private String nomeColaborador;
	
	@NotNull(message="O Número do documentpo não pode Ser Nulo")
	@Column(name="CPF_CNPJ",nullable = false)
	private String cpfCnpj;
	
	@Column(name = "SALARIO",nullable = false)
	private double salario;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Belem")
	@Column(name = "DATA_CONTRATACAO", nullable = false)
	private Date dataContratacao;
		
	@Column(name="TELEFONE")
	private String telefone;
	
	@Column(name="EMAIL")
	private String email;
	
	@ManyToOne( fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEPARTAMENTO_FK")
	private Departamento departamento;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="ID_CARGO_FK")
	private Cargo cargo;
	
	@OneToOne(mappedBy = "colaborador",fetch = FetchType.LAZY, cascade = CascadeType.ALL) //lembrar qual colocar
	private Endereco endereco;

	@JsonIgnore
	public String getNomeCargo () {
		return cargo.getNomeCargo();
	}
	
	@JsonIgnore
	public Long getIdCargo () {
		return cargo.getIdCargo();
	}
	
	@JsonIgnore
	public Long getIdDepartamento () {
		return departamento.getIdDepartamento();
	}
	
	@JsonIgnore
	public String getNomeDepartamento () {
		return departamento.getNomeDepartamento();
	}
	
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	public long getIdColaborador() {
		return idColaborador;
	}

	public void setIdColaborador(long idColaborador) {
		this.idColaborador = idColaborador;
	}

	public String getNomeColaborador() {
		return nomeColaborador;
	}
	
	public void setNomeColaborador(String nomeColaborador) {
		this.nomeColaborador = nomeColaborador;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Date getDataContratacao() {
		return dataContratacao;
	}

	public void setDataContratacao(Date dataContratacao) {
		this.dataContratacao = dataContratacao;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
