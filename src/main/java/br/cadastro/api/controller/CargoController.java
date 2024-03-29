package br.cadastro.api.controller;

import java.util.List;

import jakarta.validation.Valid;

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
import org.springframework.web.server.ResponseStatusException;

import br.cadastro.api.manager.CargoManager;
import br.cadastro.api.manager.ColaboradorManager;
import br.cadastro.api.models.Cargo;
import br.cadastro.api.models.Colaborador;
import br.cadastro.api.repository.projections.CargoDashProjection;
import br.cadastro.api.repository.projections.CargoProjection;

@RestController
@RequestMapping("gerenciamento-cargo")
public class CargoController {

	@Autowired
	private CargoManager cargoManager;

	@Autowired
	private ColaboradorManager colaboradorManager;

	@PostMapping("salvar-cargo")
	public @ResponseBody ResponseEntity<Cargo> salvarCargo(@Valid @RequestBody Cargo cargo) {
		Cargo cargoSalvo = cargoManager.salvar(cargo);
		return new ResponseEntity<Cargo>(cargoSalvo, HttpStatus.CREATED);
	}

	@PutMapping("alterar-cargo")
	public @ResponseBody ResponseEntity<Cargo> alterarCargo(@Valid @RequestBody Cargo cargo)
			throws ResponseStatusException {
		Cargo cargoLocalizado = cargoManager.buscarPorId(cargo.getIdCargo()).orElse(null);
		if (cargoLocalizado == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cargo a ser alterado não existe");
		} else {
			Cargo cargoSalvo = cargoManager.salvar(cargo);
			return new ResponseEntity<Cargo>(cargoSalvo, HttpStatus.NO_CONTENT);
		}
	}

	@DeleteMapping("deletar-cargo/{id}")
	public @ResponseBody ResponseEntity<Cargo> deletarCargo(@PathVariable Long id)
			throws ResponseStatusException, Exception {
		Cargo cargo = cargoManager.buscarPorId(id).orElse(null);
		if (cargo == null) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "O cargo de id: " + id + " não existe");
		}
		List<Colaborador> colaborador = colaboradorManager.buscarPorCargo(cargo);
		if (!colaborador.isEmpty()) {
			throw new Exception(
					"O Cargo de " + cargo.getNomeCargo() + " não pode ser excluido, pois há colaboradores associados");
		} else {
			cargoManager.deletarPorId(id);
			return new ResponseEntity<Cargo>(HttpStatus.NO_CONTENT);
		}

	}

	@GetMapping("todos-cargos")
	public @ResponseBody ResponseEntity<List<Cargo>> todosCargos() {
		List<Cargo> todosCargos = cargoManager.buscarTodos();
		return new ResponseEntity<List<Cargo>>(todosCargos, HttpStatus.OK);
	}

	@GetMapping("filtro")
	public @ResponseBody ResponseEntity<List<CargoProjection>> todosCargos(@RequestParam String tag) {
		List<CargoProjection> filtroCargos = cargoManager.buscarPorTag(tag);
		return new ResponseEntity<List<CargoProjection>>(filtroCargos, HttpStatus.OK);
	}

	@GetMapping("cargo-por-id/{id}")
	public @ResponseBody ResponseEntity<Cargo> CargoPorId(@PathVariable long id) {
		Cargo cargo = cargoManager.buscarPorId(id).orElse(null);
		return new ResponseEntity<Cargo>(cargo, HttpStatus.OK);
	}

	@GetMapping("pie-chart-data")
	public @ResponseBody ResponseEntity<List<CargoDashProjection>> chartData() {
		List<CargoDashProjection> data = cargoManager.chartData();
		return new ResponseEntity<List<CargoDashProjection>>(data, HttpStatus.OK);
	}

}
