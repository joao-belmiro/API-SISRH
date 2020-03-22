package br.cadastro.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.cadastro.api.manager.DepartamentoManager;
import br.cadastro.api.models.Departamento;

@RestController
@RequestMapping("gerenciamento-departamento")
public class DepartamentoController {
	
	@Autowired
	private DepartamentoManager departamentoManager;
	
	@PostMapping("salvar-departamento")
	public @ResponseBody ResponseEntity<Departamento> salvarDepartamento (@RequestBody Departamento departamento) {
		Departamento dep = departamentoManager.salvar(departamento);
		return new ResponseEntity<Departamento>(dep,HttpStatus.CREATED);
	}
	@PutMapping("alterar-departamento")
	public @ResponseBody ResponseEntity<Departamento> alterarDepartamento (@RequestBody Departamento departamento) {
		Departamento departamentoLocalizado = departamentoManager.buscarPorId(departamento.getIdDepartamento()).orElse(null);
		if (departamentoLocalizado != null) {
		  departamentoManager.salvar(departamento);
			return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Departamento>(HttpStatus.BAD_REQUEST);
		}
	}
	@DeleteMapping("deletar-Departamento/{id}")
	public @ResponseBody ResponseEntity<Departamento> deletarDepartamento (@PathVariable Long id) {
		departamentoManager.deletarPorId(id);
		return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("buscar-por-id/{id}")
	public @ResponseBody ResponseEntity<Departamento> departamentoPorId(@PathVariable Long id) {
		Departamento departamento = departamentoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Departamento>(departamento,HttpStatus.OK);
	}
	@GetMapping("buscar-por-tag")
	public @ResponseBody ResponseEntity<List<Departamento>> departamentoPorTag(@RequestParam String tag) {
		List<Departamento> departamentos = departamentoManager.buscarPorTag(tag);
		return new ResponseEntity<List<Departamento>>(departamentos,HttpStatus.OK);
	}

}
