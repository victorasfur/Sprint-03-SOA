package com.seuprojeto.repository;

import com.seuprojeto.model.FinancialSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialSimulationRepository extends JpaRepository<FinancialSimulation, Long> {
}