package com.calculator.app.backend;

import com.calculator.app.backend.entity.CalculatorRequest;
import com.calculator.app.backend.entity.CalculatorNormalResponse;

public interface ICalculatorService {
    abstract CalculatorNormalResponse calculate(CalculatorRequest calculatorRequest);
}
