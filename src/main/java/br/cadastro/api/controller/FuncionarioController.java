package br.cadastro.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.cadastro.api.manager.DepartamentoManager;
import br.cadastro.api.manager.FuncionarioManager;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.models.Funcionario;


@RestController
@Controller
@RequestMapping("gerenciamento-funcionario")
public class FuncionarioController {
	
	@Autowired
	private FuncionarioManager funcionarioManager;

	@Autowired
	private DepartamentoManager departamentoManager;
	
	@PostMapping("salvar-funcionario")
	public @ResponseBody ResponseEntity<Funcionario> salvarFuncionario (@RequestBody Funcionario funcionario) {
		Departamento departamentoLocalizado = departamentoManager.buscarPorId(funcionario.getDepartamento().getIdDepartamento()).orElse(null);
		if(departamentoLocalizado != null) {
		 Funcionario func = funcionarioManager.salvar(funcionario);
			return new ResponseEntity<Funcionario>(func,HttpStatus.CREATED);
		} else {
			return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
		}
	}
	@PutMapping("alterar-funcionario")
	public @ResponseBody ResponseEntity<Funcionario> alterarFuncionario (@RequestBody Funcionario funcionario) {
		Funcionario funcionarioLocalizado = funcionarioManager.buscarPorId(funcionario.getIdFuncionario()).orElse(null);
		if (funcionario == null || funcionarioLocalizado == null) {
			
		 return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
		} else {
			funcionarioManager.alterar(funcionario);
			return new ResponseEntity<Funcionario>(HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("deletar-funcionario/{id}")
	public @ResponseBody ResponseEntity<Funcionario> deletarFuncionario (@PathVariable Long id) {
		Funcionario funcionarioLocalizado = funcionarioManager.buscarPorId(id).orElse(null);
		if(funcionarioLocalizado != null) {
			funcionarioManager.deletarPorId(id);
			return new ResponseEntity<Funcionario>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Funcionario>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("buscar-por-tag")
	public @ResponseBody ResponseEntity<List<Funcionario>> buscarTag(@RequestParam(required = true) String tag){
		if (tag == null || tag == "") {
			return new ResponseEntity<List<Funcionario>>(HttpStatus.BAD_REQUEST);	
		}else {
			List<Funcionario> funcionarios =funcionarioManager.buscarPorTag(tag);
			return new ResponseEntity<List<Funcionario>>(funcionarios,HttpStatus.OK);
		}
			
	}
	
	
}

