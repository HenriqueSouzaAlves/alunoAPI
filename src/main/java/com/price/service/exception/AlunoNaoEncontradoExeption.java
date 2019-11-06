package com.price.service.exception;

public class AlunoNaoEncontradoExeption extends RuntimeException {

	private static final long serialVersionUID = 1L;


	public AlunoNaoEncontradoExeption(String mensagem) {
		super(mensagem);
	}
	
	
	public AlunoNaoEncontradoExeption(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
	
}
