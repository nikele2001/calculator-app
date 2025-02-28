package com.calculator.app.backend;

import com.calculator.app.backend.entity.CalculatorErrorResponse;
import com.calculator.app.backend.entity.CalculatorRequest;
import com.calculator.app.backend.entity.CalculatorNormalResponse;
import com.calculator.app.backend.entity.CalculatorResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class CalculatorController {

    private ICalculatorService calculatorService;

    @PostMapping(value = "/calculate")
    @ResponseBody
    public ResponseEntity<CalculatorResponse> calculate(@RequestBody CalculatorRequest request) {
        try {
            CalculatorNormalResponse response = calculatorService.calculate(request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CalculatorErrorResponse.of(e.getMessage()));
        }
    }
}
