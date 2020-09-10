package br.cadastro.api.manager;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.cadastro.api.dto.EnderecoDto;
import br.cadastro.api.models.Endereco;
import br.cadastro.api.repository.EnderecoRepository;
import br.cadastro.api.repository.projections.EnderecoProjetction;

@Service
public class EnderecoManager {

	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public EnderecoDto salvar (EnderecoDto enderecodto) {
		Endereco endereco = new Endereco();
		endereco.setCep(enderecodto.getCep());
		endereco.setComplemento(enderecodto.getComplemento());
		endereco.setNumero(enderecodto.getNumero());
		endereco.setColaborador(enderecodto.getColaborador());
		enderecoRepository.save(endereco);
	  return enderecodto;
	}
	public void alterar(Endereco endereco) {
		enderecoRepository.save(endereco);
	}
	public void deletarPorId(Long id) {
		enderecoRepository.deleteById(id);
	}
	public Optional<EnderecoProjetction> buscarPorId(Long id){
		return enderecoRepository.findByIdEndereco(id);
	}
	public List<Endereco> buscarTodos() {
		return enderecoRepository.findAll();
	} 
	
}
