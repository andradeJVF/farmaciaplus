package com.generation.farmacia.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_categoria")
public class Categorias {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(max = 100, message = "O nome da categoria é obrigatória")
	private String nome;
	
	@NotNull
	@Size(max = 500, message = "A descrição da categoria é obrigatório")
	private String descricao;
	
	@OneToMany(mappedBy = "categorias", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categorias")
	private List<Medicamentos> medicamentos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<Medicamentos> getMedicamentos() {
		return medicamentos;
	}

	public void setMedicamentos(List<Medicamentos> medicamentos) {
		this.medicamentos = medicamentos;
	}
	
}
