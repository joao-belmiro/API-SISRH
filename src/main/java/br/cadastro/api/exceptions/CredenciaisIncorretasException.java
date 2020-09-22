package br.cadastro.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class CredenciaisIncorretasException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public CredenciaisIncorretasException () {
		super("Credenciais incorretas");
	}

}
