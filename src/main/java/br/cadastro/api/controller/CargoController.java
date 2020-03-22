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

import br.cadastro.api.manager.CargoManager;
import br.cadastro.api.models.Cargo;

@RestController
@RequestMapping("geranciamento-cargo")
public class CargoController {
	
	@Autowired
	private CargoManager cargoManager;
	
	@PostMapping("salvar-cargo")
	public @ResponseBody ResponseEntity<Cargo> salvarCargo (@RequestBody Cargo cargo) {
		Cargo  cargoSalvo = cargoManager.salvar(cargo);
		return new ResponseEntity<Cargo>(cargoSalvo, HttpStatus.CREATED);
	}
	@PutMapping("alterar-cargo")
	public @ResponseBody ResponseEntity<Cargo> alterarCargo (@RequestBody Cargo cargo) {
		Cargo  cargoSalvo = cargoManager.salvar(cargo);
		return new ResponseEntity<Cargo>(cargoSalvo, HttpStatus.NO_CONTENT);
	}
	@DeleteMapping("deletar-cargo/{id}")
	public @ResponseBody ResponseEntity<Cargo> deletarCargo (@PathVariable long id) {
		cargoManager.deletarPorId(id);
		return new ResponseEntity<Cargo>(HttpStatus.NO_CONTENT);
	}
	@GetMapping("todos-cargos")
	public @ResponseBody ResponseEntity<List<Cargo>> todosCargos () {
		List<Cargo> todosCargos = cargoManager.buscarTodos();
		return new ResponseEntity<List<Cargo>>(todosCargos, HttpStatus.OK);
	}
	@GetMapping("filtro")
	public @ResponseBody ResponseEntity<List<Cargo>> todosCargos (@RequestParam String tag) {
		List<Cargo> filtroCargos = cargoManager.buscarPorTag(tag);
		return new ResponseEntity<List<Cargo>>(filtroCargos, HttpStatus.OK);
	}
	@GetMapping("cargo-por-id/{id}")
	public @ResponseBody ResponseEntity<Cargo> CargoPorId (@PathVariable long id) {
		Cargo cargo = cargoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
	}
	

}
