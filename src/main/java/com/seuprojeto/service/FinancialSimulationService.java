package com.seuprojeto.service;

import com.seuprojeto.dto.FinancialSimulationRequestDTO;
import com.seuprojeto.dto.FinancialSimulationResponseDTO;
import com.seuprojeto.model.FinancialSimulation;
import com.seuprojeto.repository.FinancialSimulationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FinancialSimulationService {

    @Autowired
    private FinancialSimulationRepository financialSimulationRepository;

    public FinancialSimulationResponseDTO createSimulation(FinancialSimulationRequestDTO requestDTO) {
        FinancialSimulation simulation = new FinancialSimulation();
        simulation.setInitialValue(requestDTO.getInitialValue());
        simulation.setMonthlyInvestment(requestDTO.getMonthlyInvestment());
        simulation.setInterestRate(requestDTO.getInterestRate());
        simulation.setMonths(requestDTO.getMonths());
        double finalValue = calculateFinalValue(simulation);
        simulation.setFinalValue(finalValue);
        financialSimulationRepository.save(simulation);
        return new FinancialSimulationResponseDTO(finalValue);
    }

    public List<FinancialSimulation> getAllSimulations() {
        return financialSimulationRepository.findAll();
    }

    private double calculateFinalValue(FinancialSimulation simulation) {
        double finalValue = simulation.getInitialValue();
        double monthlyInterestRate = simulation.getInterestRate() / 100 / 12;
        for (int i = 0; i < simulation.getMonths(); i++) {
            finalValue = (finalValue + simulation.getMonthlyInvestment()) * (1 + monthlyInterestRate);
        }
        return finalValue;
    }
}