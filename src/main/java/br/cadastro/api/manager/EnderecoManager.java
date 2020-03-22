package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cadastro.api.models.Endereco;
import br.cadastro.api.repository.EnderecoRepository;

@Service
public class EnderecoManager {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Endereco salvar (Endereco endereco) {
		return enderecoRepository.save(endereco);
	}
	public void alterar(Endereco endereco) {
		enderecoRepository.save(endereco);
	}
	public void deletarPorId(Long id) {
		enderecoRepository.deleteById(id);
	}
	public Optional<Endereco> buscarPorId(Long id){
		return enderecoRepository.findById(id);
	}
	public List<Endereco> buscarTodos() {
		return enderecoRepository.findAll();
	} 
	
}
