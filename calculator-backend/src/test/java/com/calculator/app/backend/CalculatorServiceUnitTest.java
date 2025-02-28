package com.calculator.app.backend;

import com.calculator.app.backend.entity.CalculatorException;
import com.calculator.app.backend.entity.CalculatorRequest;
import com.calculator.app.backend.entity.CalculatorNormalResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculatorServiceUnitTest {

    private CalculatorService calculatorService;

    @BeforeEach
    public void setUp() {
        calculatorService = new CalculatorService();
    }

    @Test
    public void testCalculateAdd() throws CalculatorException {
        CalculatorRequest request = new CalculatorRequest("100", "200", "add");
        CalculatorNormalResponse response = calculatorService.calculate(request);
        assertEquals("300", response.getResult());
    }

    @Test
    public void testCalculateSubtract() throws CalculatorException {
        CalculatorRequest request = new CalculatorRequest("200", "100", "subtract");
        CalculatorNormalResponse response = calculatorService.calculate(request);
        assertEquals("100", response.getResult());
    }

    @Test
    public void testCalculateInvalidNumber() {
        CalculatorRequest request = new CalculatorRequest("100a", "200", "add");
        assertThrows(CalculatorException.class, () -> calculatorService.calculate(request));
    }

    @Test
    public void testCalculateInvalidOperation() {
        CalculatorRequest request = new CalculatorRequest("100", "200", "multiply");
        assertThrows(CalculatorException.class, () -> calculatorService.calculate(request));
    }

    @Test
    public void testIsValidNumber() {
        assertTrue(CalculatorService.isValidNumber("123"));
        assertTrue(CalculatorService.isValidNumber("-123"));
        assertFalse(CalculatorService.isValidNumber("123a"));
        assertFalse(CalculatorService.isValidNumber("abc"));
    }
}