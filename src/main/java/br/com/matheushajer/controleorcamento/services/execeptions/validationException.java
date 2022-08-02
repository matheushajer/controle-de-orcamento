package br.com.matheushajer.controleorcamento.services.execeptions;

public class validationException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public validationException(String msg) {
		super(msg);
	}

}
