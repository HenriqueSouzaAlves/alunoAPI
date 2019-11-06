package br.com.aluno.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.aluno.model.Aluno;
import br.com.aluno.service.AlunoService;

@RestController
@RequestMapping("/aluno")
public class AlunoResource {

	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public ResponseEntity<List<Aluno>> buscarTodos(String aluno) {
		
		List<Aluno> alunos = alunoService.buscarTodos();
		
		return alunos != null ? ResponseEntity.status(HttpStatus.OK).body(alunos) 
				: ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Optional<Aluno>> buscarPorId(@PathVariable("id") Long id) {
		
		Optional<Aluno> aluno = alunoService.buscarPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(aluno);
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Aluno aluno) {
		
		aluno = alunoService.salvar(aluno);
		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<Void> atualizar(@RequestBody Aluno aluno, 
			@PathVariable("id") Long id) {
		
		aluno.setId(id);
		alunoService.atualizar(aluno);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
	@DeleteMapping
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		
		alunoService.deletar(id);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
	
}
