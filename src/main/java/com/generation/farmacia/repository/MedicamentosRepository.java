package com.generation.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.farmacia.model.Medicamentos;

@Repository
public interface MedicamentosRepository extends JpaRepository <Medicamentos, Long>{

	public List<Medicamentos> findAllByMedicamentoContainingIgnoreCase(@Param("medicamento")String medicamento);
	public List<Medicamentos> findByMedicamentoAndLaboratorio(String medicamento, String laboratorio);
	public List<Medicamentos> findByMedicamentoOrLaboratorio(String medicamento, String laboratorio);
}