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

import com.generation.farmacia.model.Categorias;
import com.generation.farmacia.repository.CategoriasRepository;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriasController {
	
	@Autowired CategoriasRepository categoriasRepository;
	
	@GetMapping
	public ResponseEntity <List<Categorias>> getAll(){
		return ResponseEntity.ok(categoriasRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity <Categorias> getById(@PathVariable Long id){
		return categoriasRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/nome/{nome}")
	public ResponseEntity <List<Categorias>> getByNome(@PathVariable String nome){
		return ResponseEntity.ok(categoriasRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	@PostMapping
	public ResponseEntity <Categorias> postCategoria(@Valid @RequestBody Categorias categorias){
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriasRepository.save(categorias));
	}
	
	@PutMapping
	public ResponseEntity <Categorias> putCategoria(@Valid @RequestBody Categorias categorias){
		return categoriasRepository.findById(categorias.getId())
				.map(resposta -> ResponseEntity.ok().body(categoriasRepository.save(categorias)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity <?> deleteCategoria(@PathVariable Long id){
		
		return categoriasRepository.findById(id)
				.map(Resposta -> {
					categoriasRepository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
				.orElse(ResponseEntity.notFound().build());
	}
}