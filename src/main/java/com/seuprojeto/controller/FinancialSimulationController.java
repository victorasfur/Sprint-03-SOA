package com.seuprojeto.controller;

import com.seuprojeto.dto.FinancialSimulationRequestDTO;
import com.seuprojeto.dto.FinancialSimulationResponseDTO;
import com.seuprojeto.model.FinancialSimulation;
import com.seuprojeto.service.FinancialSimulationService;
import com.seuprojeto.service.GamificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/simulations")
public class FinancialSimulationController {

    @Autowired
    private FinancialSimulationService financialSimulationService;

    @Autowired
    private GamificationService gamificationService;

    @PostMapping("/simulate")
    public ResponseEntity<FinancialSimulationResponseDTO> createSimulation(@RequestBody FinancialSimulationRequestDTO requestDTO) {
        FinancialSimulationResponseDTO response = financialSimulationService.createSimulation(requestDTO);
        gamificationService.updateScore(1L, com.seuprojeto.enums.ActivityType.SIMULATE_FINANCIAL_PLAN, 5);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FinancialSimulation>> getAllSimulations() {
        List<FinancialSimulation> simulations = financialSimulationService.getAllSimulations();
        return new ResponseEntity<>(simulations, HttpStatus.OK);
    }
}