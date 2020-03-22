package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Funcionario;
import br.cadastro.api.repository.FuncionarioRepository;



@Service
public class FuncionarioManager {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario salvar (Funcionario funcionario) {
	 return	funcionarioRepository.save(funcionario);
	}
	public void alterar (Funcionario funcionario) {
		funcionarioRepository.save(funcionario);
	}
	public void deletarPorId (Long id) {
		funcionarioRepository.deleteById(id);
	}
	public Optional<Funcionario> buscarPorId (Long id) {
		return funcionarioRepository.findById(id);
	}
	public List<Funcionario> buscarTodos() {
		return funcionarioRepository.findAll();	
	}
	public List<Funcionario> buscarPorTag(String tag){
		return funcionarioRepository.findByTag(tag);
	}
	public List<Funcionario> filtro(Funcionario filtro) {
		Example<Funcionario> example = Example.of(filtro);
		return funcionarioRepository.findAll(example);
	}
	
	
}
