package br.com.aluno.handler;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.price.service.exception.AlunoNaoEncontradoExeption;

import br.com.aluno.model.DetalhesErro;

@ControllerAdvice
public class ResourceExceptionHandler {

	public String dateFormat() {
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		LocalDate localDate = LocalDate.now();
		LocalDateTime now = LocalDateTime.now(); 
		Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
		date.setHours(now.getHour()-1);
		date.setMinutes(now.getMinute());
		return fmt.format(date);
	}
	
	@ExceptionHandler(AlunoNaoEncontradoExeption.class)
	public ResponseEntity<DetalhesErro> handleAlunoNaoEncontradoException
							(AlunoNaoEncontradoExeption e, HttpServletRequest servlet) {
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404L);
		erro.setTitulo("Alunos não encontrados.");
		erro.setDataHora(dateFormat());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
}
