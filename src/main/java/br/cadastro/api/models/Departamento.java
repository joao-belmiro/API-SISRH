package br.cadastro.api.models;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "DEPARTAMENTO")
public class Departamento implements Serializable {

	private static final long serialVersionUID = 1165645297758517910L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_DEPARTAMENTO")
	private Long idDepartamento;

	@NotBlank(message = "O nome do departamento não pode ser um espaço vazio")
	@Column(name = "NOME_DEPARTAMENTO", nullable = false)
	private String nomeDepartamento;

	@NotBlank(message = "A descrição do departamento não pode ser um espaço vazio")
	@Column(name = "DESCRICAO_DEPARTAMENTO")
	private String descricao;

	@JsonBackReference
	@OneToMany(mappedBy = "departamento", fetch = FetchType.LAZY)
	private List<Colaborador> colaboradores;

	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

	public Long getIdDepartamento() {
		return idDepartamento;
	}

	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(List<Colaborador> colaborador) {
		this.colaboradores = colaborador;
	}

	@JsonIgnore
	public int getNColaboradores() {
		return colaboradores.size();
	}

	@JsonIgnore
	public double getCustoDepartamento() {
		return colaboradores.stream().mapToDouble(colab -> colab.getSalario()).sum();
	}

	@JsonIgnore
	public Map<String, Long> getCountCargos() {
		Map<String, Long> grafico = colaboradores.stream()
				.collect(Collectors.groupingBy(c -> c.getNomeCargo(), Collectors.counting()));
		return grafico;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
