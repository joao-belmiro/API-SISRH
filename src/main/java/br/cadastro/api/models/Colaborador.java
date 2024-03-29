package br.cadastro.api.models;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "COLABORADOR")
public class Colaborador implements Serializable {

	private static final long serialVersionUID = 5240480156666113067L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_COLABORADOR")
	private Long idColaborador;

	@NotNull(message = "Nome do Colaborador não pode Ser Nulo")
	@Column(name = "NOME_COLABORADOR", nullable = false)
	private String nomeColaborador;

	@NotNull(message = "O Número do documento não pode Ser Nulo")
	@Column(name = "CPF_CNPJ", nullable = false)
	private String cpfCnpj;

	@Column(name = "SALARIO", nullable = false)
	private Double salario;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy", locale = "pt-BR", timezone = "America/Belem")
	@Column(name = "DATA_CONTRATACAO", nullable = false)
	private Date dataContratacao;

	@NotBlank(message = "O Telefone não pode ser um espaço em branco")
	@Column(name = "TELEFONE", nullable = false)
	private String telefone;

	@Email
	@NotBlank(message = "O E-mail não pode ser um espaço em branco")
	@Column(name = "EMAIL", nullable = false)
	private String email;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEPARTAMENTO_FK", nullable = false)
	private Departamento departamento;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_CARGO_FK", nullable = false)
	private Cargo cargo;

	@OneToOne(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Endereco endereco;

	@JsonIgnore
	public String getNomeCargo() {
		return cargo.getNomeCargo();
	}

	@JsonIgnore
	public Long getIdCargo() {
		return cargo.getIdCargo();
	}

	@JsonIgnore
	public Long getIdDepartamento() {
		return departamento.getIdDepartamento();
	}

	@JsonIgnore
	public String getNomeDepartamento() {
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
