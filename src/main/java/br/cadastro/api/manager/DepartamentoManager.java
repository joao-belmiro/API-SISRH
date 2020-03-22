package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.DepartamentoRepository;

@Service
public class DepartamentoManager {

	@Autowired
	private DepartamentoRepository departamentoRepository;
	
	public Departamento salvar (Departamento departamento) {
	 return	departamentoRepository.save(departamento);
	}
	public void alterar (Departamento departamento) {
		departamentoRepository.save(departamento);
	}
	public void deletarPorId (Long id) {
		departamentoRepository.deleteById(id);	
	}
	public Optional<Departamento> buscarPorId (Long id) {
		return departamentoRepository.findById(id);
	}
	public List<Departamento> buscarTodos (){
		return departamentoRepository.findAll();
	}
	public List<Departamento> buscarPorTag (String tag){
		return departamentoRepository.findByTag(tag);
	}
	
}
