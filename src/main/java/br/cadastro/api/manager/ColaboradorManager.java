package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Cargo;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.ColaboradorRepository;

@Service
public class ColaboradorManager {
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	public Colaborador salvar (Colaborador funcionario) {
	 return	colaboradorRepository.save(funcionario);
	}
	public void alterar (Colaborador funcionario) {
	   colaboradorRepository.save(funcionario);
	}
	public void deletarPorId (Long id) {
		colaboradorRepository.deleteById(id);
	}
	public Optional<Colaborador> buscarPorId (Long id) {
		return colaboradorRepository.findById(id);
	}
	public List<Colaborador> buscarTodos() {
		return colaboradorRepository.findAll();	
	}
	public List<Colaborador> buscarPorTag(String tag){
		return colaboradorRepository.findByTag(tag);
	}
	public List<Colaborador> filtro(Colaborador filtro) {
		Example<Colaborador> example = Example.of(filtro);
		return colaboradorRepository.findAll(example);
	}
	public List<Colaborador> novosColabs () {
		return colaboradorRepository.findTop10By();		
	}
	public List<Colaborador> colaboradoresDepartamento (Departamento departamento) {
		return colaboradorRepository.findByDepartamento(departamento);
	}
	public Optional<Colaborador> buscarPorCargo (Cargo cargo) {
		return colaboradorRepository.findByCargo(cargo);
	}
}
