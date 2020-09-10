package br.cadastro.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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

import br.cadastro.api.manager.DepartamentoManager;
import br.cadastro.api.manager.ColaboradorManager;
import br.cadastro.api.models.Departamento;
import br.cadastro.api.models.Colaborador;

@CrossOrigin("*")
@RestController
@Controller
@RequestMapping("gerenciamento-colaborador")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorManager funcionarioManager;

	@Autowired
	private DepartamentoManager departamentoManager;
	
	@PostMapping("salvar-colaborador")
	public @ResponseBody ResponseEntity<Colaborador> salvarFuncionario (@RequestBody Colaborador colaborador) throws IllegalArgumentException {
		Departamento departamentoLocalizado = departamentoManager.buscarPorId(colaborador.getDepartamento().getIdDepartamento()).orElse(null);
		if(departamentoLocalizado != null) {
		 Colaborador func = funcionarioManager.salvar(colaborador);
			return new ResponseEntity<Colaborador>(func,HttpStatus.CREATED);
		} else {
			throw new IllegalArgumentException("Verifique Se o Cadastro esta completo");
		}
	}
	@PutMapping("alterar-colaborador")
	public @ResponseBody ResponseEntity<Colaborador> alterarFuncionario (@RequestBody Colaborador funcionario) {
		Colaborador funcionarioLocalizado = funcionarioManager.buscarPorId(funcionario.getIdColaborador()).orElse(null);
		if (funcionario == null || funcionarioLocalizado == null) {
			
		 return new ResponseEntity<Colaborador>(HttpStatus.BAD_REQUEST);
		} else {
			funcionarioManager.alterar(funcionario);
			return new ResponseEntity<Colaborador>(HttpStatus.NO_CONTENT);
		}
	}
	@DeleteMapping("deletar/{id}")
	public @ResponseBody ResponseEntity<Colaborador> deletarFuncionario (@PathVariable Long id) {
		Colaborador funcionarioLocalizado = funcionarioManager.buscarPorId(id).orElse(null);
		if(funcionarioLocalizado != null) {
			funcionarioManager.deletarPorId(id);
			return new ResponseEntity<Colaborador>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Colaborador>(HttpStatus.BAD_REQUEST);
		}
	}
	@GetMapping("buscar-por-tag")
	public @ResponseBody ResponseEntity<List<Colaborador>> buscarTag(@RequestParam(required = true) String tag) {
		if (tag == null || tag == "") {
			return new ResponseEntity<List<Colaborador>>(HttpStatus.BAD_REQUEST);	
		} else {
			List<Colaborador> funcionarios =funcionarioManager.buscarPorTag(tag);
			return new ResponseEntity<List<Colaborador>>(funcionarios,HttpStatus.OK);
		}	
	}
	@GetMapping("/{id}")
	public @ResponseBody ResponseEntity<Colaborador> buscarPorId(@PathVariable Long id) {
		Colaborador funcionarioLocalizado = funcionarioManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Colaborador>(funcionarioLocalizado, HttpStatus.OK);
	}
	@GetMapping("novos-colabs")
	public @ResponseBody ResponseEntity<List<Colaborador>> novosColabs () {
		List<Colaborador> colabs = funcionarioManager.novosColabs();
		return new ResponseEntity<List<Colaborador>>(colabs, HttpStatus.OK);
	}
	@GetMapping("colaboradores-departamento")
	public @ResponseBody ResponseEntity<List<Colaborador>> colabsDepartamento(@RequestParam(required = true) long idDepartamento) {
		Departamento departamento = departamentoManager.buscarPorId(idDepartamento).orElse(null);
		List<Colaborador> colabsDepartamento = funcionarioManager.colaboradoresDepartamento(departamento);
		return new ResponseEntity<List<Colaborador>>(colabsDepartamento, HttpStatus.OK);
				
	}
	
}

