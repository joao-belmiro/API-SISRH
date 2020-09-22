package br.cadastro.api.dto;

public class TokenDto {
	
	private String login;
	private String token;
	private Boolean adm;
	
	public Boolean getAdm() {
		return adm;
	}
	public void setAdm(Boolean adm) {
		this.adm = adm;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	
}
