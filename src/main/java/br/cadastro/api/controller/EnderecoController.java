package br.cadastro.api.controller;

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

import br.cadastro.api.manager.EnderecoManager;
import br.cadastro.api.manager.FuncionarioManager;
import br.cadastro.api.models.Endereco;
import br.cadastro.api.models.Funcionario;

@RestController
@RequestMapping("gerenciamento-endereco")
public class EnderecoController {

	@Autowired
	private EnderecoManager enderecoManager;
	
	@Autowired
	private FuncionarioManager funcionarioManager;
	
	@PostMapping("salvar-endereco")
	public @ResponseBody ResponseEntity<Endereco> salvarEndereco(@RequestBody Endereco endereco) {
		Funcionario funcionarioLoacalizado = funcionarioManager.buscarPorId(endereco.getFuncionario().getIdFuncionario()).orElse(null);
		if (funcionarioLoacalizado == null) {
			return new  ResponseEntity<Endereco>(endereco,HttpStatus.BAD_REQUEST);
		} else {
			Endereco enderecoCriado = enderecoManager.salvar(endereco);
			return new ResponseEntity<Endereco>(enderecoCriado,HttpStatus.CREATED);
		}
		
	}
	@PutMapping("alterar-endereco")
	public @ResponseBody ResponseEntity<Endereco> alterarEndereco(@RequestBody Endereco endereco) {
		Funcionario funcionarioLoacalizado = funcionarioManager.buscarPorId(endereco.getFuncionario().getIdFuncionario()).orElse(null);
		if (endereco.getIdEndereco() == 0 || funcionarioLoacalizado == null) {
			return new  ResponseEntity<Endereco>(endereco,HttpStatus.BAD_REQUEST);
		} else {
			enderecoManager.salvar(endereco);
			return new ResponseEntity<Endereco>(HttpStatus.NO_CONTENT);
		}
		
	}
	@DeleteMapping("deletar-endereco/{id}")
	public @ResponseBody ResponseEntity<Endereco> deletarEndereco(@PathVariable long id) {
		Endereco enderecoLocalizado = enderecoManager.buscarPorId(id).orElse(null);
		if (enderecoLocalizado != null) {
			enderecoManager.deletarPorId(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("endereco-id/{id}")
	public @ResponseBody ResponseEntity<Endereco> buscarPorId(@PathVariable long id) {
		Endereco endereco = enderecoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Endereco>(endereco,HttpStatus.OK);
	}
	
}
