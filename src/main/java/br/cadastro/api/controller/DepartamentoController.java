package br.cadastro.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import br.cadastro.api.manager.ColaboradorManager;
import br.cadastro.api.manager.DepartamentoManager;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.projections.DepartamentoDash;
import br.cadastro.api.repository.projections.DepartamentoProjection;
import javassist.NotFoundException;

@CrossOrigin("*")
@RestController
@RequestMapping("gerenciamento-departamento")
public class DepartamentoController {

	@Autowired
	private DepartamentoManager departamentoManager;
	
	@Autowired
	private ColaboradorManager colaboradorManager;

	@PostMapping("salvar-departamento")
	public @ResponseBody ResponseEntity<Departamento> salvarDepartamento(@RequestBody Departamento departamento)
			throws NullPointerException {
		if (departamento.getNomeDepartamento() == null || departamento.getNomeDepartamento() == "") {
			throw new NullPointerException("Digite um Nome para o Departamento");
		} else {
			Departamento dep = departamentoManager.salvar(departamento);
			return new ResponseEntity<Departamento>(dep, HttpStatus.CREATED);
		}
	}

	@PutMapping("alterar-departamento")
	public @ResponseBody ResponseEntity<Departamento> alterarDepartamento(@RequestBody Departamento departamento) throws NotFoundException {
		Departamento departamentoLocalizado = departamentoManager.buscarPorId(departamento.getIdDepartamento())
				.orElse(null);
		if (departamentoLocalizado != null) {
			departamentoManager.salvar(departamento);
			return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);
		} else {
			throw new NotFoundException("Departamento Inexistente");
		}
	}
	@DeleteMapping("deletar-departamento/{id}")
	public @ResponseBody ResponseEntity<Departamento> deletarDepartamento(@PathVariable Long id) throws NotFoundException , Exception {
		Departamento deptLocalizado = departamentoManager.buscarPorId(id).orElse(null);
		List<Colaborador> colabs = colaboradorManager.colaboradoresDepartamento(deptLocalizado);
		if (!colabs.isEmpty()) {
			throw new Exception("Não é possível Excluir um Departamento com Colaboradores");
		}
		if(deptLocalizado == null) {
			throw new NotFoundException("Departamento inexistente");
		} else {
			departamentoManager.deletarPorId(id);
			return new ResponseEntity<Departamento>(HttpStatus.NO_CONTENT);						
		}
	}

	@GetMapping("buscar-por-id/{id}")
	public @ResponseBody ResponseEntity<Departamento> departamentoPorId(@PathVariable Long id) {
		Departamento departamento = departamentoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
	}

	@GetMapping("buscar-por-tag")
	public @ResponseBody ResponseEntity<List<DepartamentoProjection>> departamentoPorTag(@RequestParam String tag) {
		List<DepartamentoProjection> departamentos = departamentoManager.buscarPorTag(tag);
		return new ResponseEntity<List<DepartamentoProjection>>(departamentos, HttpStatus.OK);
	}

	@GetMapping("todos")
	public @ResponseBody ResponseEntity<List<Departamento>> departamentoPorTag() {
		List<Departamento> departamentos = departamentoManager.buscarTodos();
		return new ResponseEntity<List<Departamento>>(departamentos, HttpStatus.OK);
	}

	@GetMapping("popular-dash")
	public @ResponseBody ResponseEntity<List<DepartamentoDash>> departamentoDashBoard() {
		List<DepartamentoDash> departamentos = departamentoManager.PopularDash();
		return new ResponseEntity<List<DepartamentoDash>>(departamentos, HttpStatus.OK);
	}

}
