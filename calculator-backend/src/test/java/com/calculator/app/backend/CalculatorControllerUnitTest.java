package com.calculator.app.backend;

import com.calculator.app.backend.entity.CalculatorErrorResponse;
import com.calculator.app.backend.entity.CalculatorRequest;
import com.calculator.app.backend.entity.CalculatorNormalResponse;
import com.calculator.app.backend.entity.CalculatorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CalculatorControllerUnitTest {

    @Mock
    private ICalculatorService calculatorService;

    @InjectMocks
    private CalculatorController calculatorController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateSuccess() {
        CalculatorRequest request = new CalculatorRequest("100", "200", "add");
        CalculatorNormalResponse response = new CalculatorNormalResponse("300");

        when(calculatorService.calculate(any(CalculatorRequest.class))).thenReturn(response);

        ResponseEntity<CalculatorResponse> result = calculatorController.calculate(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void testCalculateError() {
        CalculatorRequest request = new CalculatorRequest("100", "200", "add");
        String errorMessage = "Invalid operation";

        when(calculatorService.calculate(any(CalculatorRequest.class))).thenThrow(new RuntimeException(errorMessage));

        ResponseEntity<CalculatorResponse> result = calculatorController.calculate(request);

        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals(errorMessage, ((CalculatorErrorResponse) result.getBody()).getError());
    }
}