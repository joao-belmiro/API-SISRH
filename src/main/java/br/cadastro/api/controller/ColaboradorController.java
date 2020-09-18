package br.cadastro.api.controller;

import java.util.List;

import javax.validation.ValidationException;

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
import br.cadastro.api.manager.ColaboradorManager;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.repository.projections.ColaboradorProjection;
import javassist.NotFoundException;
import br.cadastro.api.models.Colaborador;

@RestController
@Controller
@RequestMapping("gerenciamento-colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorManager colaboradorManager;

	@Autowired
	private DepartamentoManager departamentoManager;

	@PostMapping("salvar-colaborador")
	public @ResponseBody ResponseEntity<Colaborador> salvarFuncionario(@RequestBody Colaborador colaborador)
			throws IllegalArgumentException, ValidationException, NullPointerException {
		if (colaborador.getDepartamento().getIdDepartamento() == null) {
			throw new IllegalArgumentException("Não é possivel cadastrar um colaborador sem Departamento");
		}
		if (colaborador.getCargo().getIdCargo() == null) {
			throw new IllegalArgumentException("Não é possivel cadastrar um Colaborador sem Cargo");
		} else {
			Colaborador func = colaboradorManager.salvar(colaborador);
			return new ResponseEntity<Colaborador>(func, HttpStatus.CREATED);
		}
	}

	@PutMapping("alterar-colaborador")
	public @ResponseBody ResponseEntity<Colaborador> alterarFuncionario(@RequestBody Colaborador colaborador)
			throws NotFoundException {
		Colaborador funcionarioLocalizado = colaboradorManager.buscarPorId(colaborador.getIdColaborador()).orElse(null);
		if (funcionarioLocalizado == null) {
			throw new NotFoundException("Colaborador Inexistente");
		} else {
			colaboradorManager.alterar(colaborador);
			return new ResponseEntity<Colaborador>(HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("deletar/{id}")
	public @ResponseBody ResponseEntity<Colaborador> deletarFuncionario(@PathVariable Long id)
			throws NotFoundException {
		Colaborador funcionarioLocalizado = colaboradorManager.buscarPorId(id).orElse(null);
		if (funcionarioLocalizado != null) {
			colaboradorManager.deletarPorId(id);
			return new ResponseEntity<Colaborador>(HttpStatus.NO_CONTENT);
		} else {
			throw new NotFoundException("Colaborador não pode ser localizado ou nao existe");
		}
	}
	@GetMapping("buscar-por-tag")
	public @ResponseBody ResponseEntity<List<ColaboradorProjection>> buscarTag(@RequestParam(required = true) String tag) {
		if (tag == null || tag == "") {
			return new ResponseEntity<List<ColaboradorProjection>>(HttpStatus.BAD_REQUEST);
		} else {
			List<ColaboradorProjection> funcionarios = colaboradorManager.buscarPorTag(tag);
			return new ResponseEntity<List<ColaboradorProjection>>(funcionarios, HttpStatus.OK);
		}
	}
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Colaborador> buscarPorId(@PathVariable Long id) throws NotFoundException {
		Colaborador funcionarioLocalizado = colaboradorManager.buscarPorId(id).orElse(null);
		if (funcionarioLocalizado == null) {
			throw new NotFoundException("O Código" + id + "não Localizou Nenhum Colaborador");
		} else {
			return new ResponseEntity<Colaborador>(funcionarioLocalizado, HttpStatus.OK);
		}
	}
	@GetMapping("novos-colabs")
	public @ResponseBody ResponseEntity<List<Colaborador>> novosColabs() {
		List<Colaborador> colabs = colaboradorManager.novosColabs();
		return new ResponseEntity<List<Colaborador>>(colabs, HttpStatus.OK);
	}
	@GetMapping("colaboradores-departamento")
	public @ResponseBody ResponseEntity<List<Colaborador>> colabsDepartamento(
			@RequestParam(required = true) long idDepartamento) {
		Departamento departamento = departamentoManager.buscarPorId(idDepartamento).orElse(null);
		List<Colaborador> colabsDepartamento = colaboradorManager.colaboradoresDepartamento(departamento);
		return new ResponseEntity<List<Colaborador>>(colabsDepartamento, HttpStatus.OK);

	}

}
