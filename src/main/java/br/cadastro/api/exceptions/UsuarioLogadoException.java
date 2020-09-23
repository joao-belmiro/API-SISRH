package br.cadastro.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN)
public class UsuarioLogadoException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public UsuarioLogadoException(String mensagem) {
		super(mensagem);
	}
}
