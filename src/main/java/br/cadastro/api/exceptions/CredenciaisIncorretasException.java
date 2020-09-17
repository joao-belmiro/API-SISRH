package br.cadastro.api.exceptions;

public class CredenciaisIncorretasException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CredenciaisIncorretasException () {
		super("Credenciais incorretas");
	}

}
