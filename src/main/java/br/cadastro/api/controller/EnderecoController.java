package br.cadastro.api.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.cadastro.api.dto.EnderecoDto;
import br.cadastro.api.manager.EnderecoManager;
import br.cadastro.api.manager.ColaboradorManager;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.repository.projections.EnderecoProjetction;

@RestController
@RequestMapping("gerenciamento-endereco")
public class EnderecoController {

	@Autowired
	private EnderecoManager enderecoManager;
	
	@Autowired
	private ColaboradorManager funcionarioManager;
	
	@PostMapping("salvar-endereco")
	public @ResponseBody ResponseEntity<EnderecoDto> salvarEndereco(@Valid @RequestBody EnderecoDto enderecodto) {
		Colaborador funcionarioLoacalizado = funcionarioManager.buscarPorId(enderecodto.getColaborador().getIdColaborador()).orElse(null);
		if (funcionarioLoacalizado == null) {
			return new  ResponseEntity<EnderecoDto>(enderecodto,HttpStatus.BAD_REQUEST);
		} else {
			EnderecoDto enderecoCriado = enderecoManager.salvar(enderecodto);
			return new ResponseEntity<EnderecoDto>(enderecoCriado,HttpStatus.CREATED);
		}
		
	}
	@PutMapping("alterar-endereco")
	public @ResponseBody ResponseEntity<EnderecoDto> alterarEndereco(@Valid @RequestBody EnderecoDto enderecodto) {
		Colaborador funcionarioLoacalizado = funcionarioManager.buscarPorId(enderecodto.getColaborador().getIdColaborador()).orElse(null);
		if (enderecodto.getIdEndereco() == 0 || funcionarioLoacalizado == null) {
			return new  ResponseEntity<EnderecoDto>(HttpStatus.BAD_REQUEST);
		} else {
			enderecoManager.salvar(enderecodto);
			return new ResponseEntity<EnderecoDto>(HttpStatus.NO_CONTENT);
		}
		
	}
	@DeleteMapping("deletar-endereco/{id}")
	public @ResponseBody ResponseEntity<EnderecoProjetction> deletarEndereco(@PathVariable long id) {
		EnderecoProjetction enderecoLocalizado = enderecoManager.buscarPorId(id).orElse(null);
		if (enderecoLocalizado != null) {
			enderecoManager.deletarPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("endereco-id/{id}")
	public @ResponseBody ResponseEntity<EnderecoProjetction> buscarPorId(@PathVariable long id) {
		EnderecoProjetction endereco = enderecoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<EnderecoProjetction>(endereco,HttpStatus.OK);
	}
	
}
