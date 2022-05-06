package com.generation.farmacia.controller;

import java.util.List;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RestController;

import com.generation.farmacia.model.Medicamentos;
import com.generation.farmacia.repository.CategoriasRepository;
import com.generation.farmacia.repository.MedicamentosRepository;

@RestController
@RequestMapping("/medicamentos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MedicamentosController {
	
	@Autowired MedicamentosRepository medicamentosRepository;
	@Autowired CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity <List<Medicamentos>> getAll(){
		return ResponseEntity.ok(medicamentosRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Medicamentos> getById(@PathVariable Long id){
		return medicamentosRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());	
	}
	
	@GetMapping("/medicamento/{medicamento}")
	public ResponseEntity<List<Medicamentos>> getByMedicamento(@PathVariable String medicamento){
		return ResponseEntity.ok(medicamentosRepository.findAllByMedicamentoContainingIgnoreCase(medicamento));
	}
	
	@GetMapping("/medicamento/{medicamento}/elaboratorio/{laboratorio}")
	public ResponseEntity<List<Medicamentos>> getByMedicamentoAndLaboratorio(@PathVariable String medicamento, @PathVariable String laboratorio){
		return ResponseEntity.ok(medicamentosRepository.findByMedicamentoAndLaboratorio(medicamento, laboratorio));
	}
	
	@GetMapping("/medicamento/{medicamento}/oulaboratorio/{laboratorio}")
	public ResponseEntity<List<Medicamentos>> getByMedicamentoOrLaboratorio(@PathVariable String medicamento, @PathVariable String laboratorio){
		return ResponseEntity.ok(medicamentosRepository.findByMedicamentoOrLaboratorio(medicamento, laboratorio));
	}
	
	@PostMapping
	public ResponseEntity <Medicamentos> postMedicamento(@Valid @RequestBody Medicamentos medicamentos){
		return ResponseEntity.status(HttpStatus.CREATED).body(medicamentosRepository.save(medicamentos));
	}
	
	@PutMapping
	public ResponseEntity <Medicamentos> putProduto(@Valid @RequestBody Medicamentos medicamentos){
		
		if (medicamentosRepository.existsById(medicamentos.getId())) {
			
			if (categoriasRepository.existsById(medicamentos.getCategorias().getId()))
				return ResponseEntity.status(HttpStatus.OK).body(medicamentosRepository.save(medicamentos));
		
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deleteMedicamento(@PathVariable Long id){
		
		return medicamentosRepository.findById(id)
				.map(resposta -> {
					medicamentosRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}

}
