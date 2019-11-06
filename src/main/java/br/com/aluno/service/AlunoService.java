package br.com.aluno.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.price.service.exception.AlunoNaoEncontradoExeption;

import br.com.aluno.model.Aluno;
import br.com.aluno.repository.AlunoRepository;

@Service
public class AlunoService {

	//private static final Logger LOGGER = LoggerFactory.getLogger(AlunoService.class);
	
	@Autowired
	private AlunoRepository alunoRepository;
	
	public List<Aluno> buscarTodos() {
		
		List<Aluno> alunos = alunoRepository.findAll();
		
		if(alunos.isEmpty()) {
			throw new AlunoNaoEncontradoExeption("Alunos não encontrados.");
		}
		
		return alunos;
	}
	
	public Optional<Aluno> buscarPorId(Long id) {
		
		Optional<Aluno> aluno = alunoRepository.findById(id);
		
		if(!aluno.isPresent()) {
			throw new AlunoNaoEncontradoExeption("O Id cliente não pode ser encontrato!");
		}
		
		return aluno;
	}
	
	public Aluno salvar(Aluno aluno) {
		
		if (aluno.getId() != null) {
			
			Optional<Aluno> a = alunoRepository.findById(aluno.getId());
			
			if(a != null) {
				throw new AlunoNaoEncontradoExeption("Aluno já existe");
			}
		}
		
		return alunoRepository.save(aluno);
	}
	
	public void atualizar(Aluno aluno) {
		verificarExistencia(aluno);
		alunoRepository.save(aluno);
	}
	
	public void deletar(Long id) {
		try {
			alunoRepository.deleteById(id);
		
		
		} catch (EmptyResultDataAccessException e) {
			throw new AlunoNaoEncontradoExeption("O Aluno não pode ser encontrado.");
		}
		
	}
	
	public void verificarExistencia(Aluno aluno) {
		buscarPorId(aluno.getId());
	}
	
}
