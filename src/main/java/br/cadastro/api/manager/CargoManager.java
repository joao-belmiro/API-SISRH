package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Cargo;
import br.cadastro.api.repository.CargoRepository;

@Service
public class CargoManager {
	
	@Autowired
	private CargoRepository cargoRepository;
	
	public Cargo salvar(Cargo cargo) {
		return cargoRepository.save(cargo);
	}
	public void alterar (Cargo cargo) {
		cargoRepository.save(cargo);
	}
	public void deletarPorId(Long id) {
		cargoRepository.deleteById(id);
	}
	public Optional<Cargo> buscarPorId(Long id){
		return cargoRepository.findById(id);
	}
	public List<Cargo> buscarTodos () {
		return cargoRepository.findAll();
	}
	public List<Cargo> buscarPorTag(String tag){
		return cargoRepository.findByTag(tag);
	}

}
