package br.cadastro.api.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "USUARIOS")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_USUARIO")
	private Long id;
	
	@Column(name = "LOGIN" , nullable = false)
	@NotEmpty(message = "O login Não pode ser um espaço vazio")
	private String login;
	
	@Column(name = "SENHA", nullable = false)
	@NotEmpty(message = "A senha Não pode ser um espaço vazio")
	private String senha;
	
	@Column(name = "ADMIN",nullable = false)
	@NotBlank(message = "Tipo de Administrador Não pode ser um espaço vazio")
	private Boolean admin;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Boolean getAdmin() {
		return admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}
	
}
