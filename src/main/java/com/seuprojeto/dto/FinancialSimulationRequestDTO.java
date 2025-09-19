package com.seuprojeto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FinancialSimulationRequestDTO {
    private double initialValue;
    private double monthlyInvestment;
    private double interestRate;
    private int months;
}