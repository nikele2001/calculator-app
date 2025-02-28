package com.calculator.app.backend;

import com.calculator.app.backend.entity.CalculatorException;
import com.calculator.app.backend.entity.CalculatorRequest;
import com.calculator.app.backend.entity.CalculatorNormalResponse;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements ICalculatorService {
    @Override
    public CalculatorNormalResponse calculate(CalculatorRequest calculatorRequest) throws CalculatorException {
        String num1 = calculatorRequest.getNum1();
        String num2 = calculatorRequest.getNum2();
        String operation = calculatorRequest.getOperation();

        throwIfInvalidNumberString(num1, num2);
        throwIfInvalidOperation(operation);

        if (operation.equals("add")) {
            return new CalculatorNormalResponse(add(num1, num2));
        } else { // We have checked to ensure it is a valid operation; safe to assume it is subtraction at this point
            return new CalculatorNormalResponse(subtract(num1, num2));
        }
    }

    private static String add(String num1, String num2) {
        if (num1.startsWith("-") && num2.startsWith("-")) {
            return "-" + add(num1.substring(1), num2.substring(1));
        } else if (num1.startsWith("-")) {
            return subtract(num2, num1.substring(1));
        } else if (num2.startsWith("-")) {
            return subtract(num1, num2.substring(1));
        }

        StringBuilder result = new StringBuilder();
        int carry = 0, i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0 || j >= 0 || carry > 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;
            int sum = digit1 + digit2 + carry;
            carry = sum / 10;
            result.append(sum % 10);
        }

        return result.reverse().toString();
    }

    private static String subtract(String num1, String num2) {
        if (num2.startsWith("-")) return add(num1, num2.substring(1));
        if (num1.startsWith("-")) return "-" + add(num1.substring(1), num2);

        if (isSmaller(num1, num2)) {
            return "-" + subtract(num2, num1);
        }

        StringBuilder result = new StringBuilder();
        int borrow = 0, i = num1.length() - 1, j = num2.length() - 1;

        while (i >= 0 || j >= 0) {
            int digit1 = (i >= 0) ? num1.charAt(i--) - '0' : 0;
            int digit2 = (j >= 0) ? num2.charAt(j--) - '0' : 0;

            digit1 -= borrow;
            if (digit1 < digit2) {
                digit1 += 10;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.append(digit1 - digit2);
        }

        while (result.length() > 1 && result.charAt(result.length() - 1) == '0') {
            result.deleteCharAt(result.length() - 1);
        }

        return result.reverse().toString();
    }

    private static void throwIfInvalidNumberString(String num1, String num2) {
        if (!isValidNumber(num1)) {
            throw new CalculatorException("An invalid string has been passed to num1.");
        }
        if (!isValidNumber(num2)) {
            throw new CalculatorException("An invalid string has been passed to num2.");
        }
    }

    private static void throwIfInvalidOperation(String operation) {
        if (!operation.equals("add") && !operation.equals("subtract")) {
            throw new CalculatorException("An unknown operation has been passed.");
        }
    }

    private static boolean isSmaller(String num1, String num2) {
        if (num1.length() != num2.length()) return num1.length() < num2.length();
        return num1.compareTo(num2) < 0;
    }

    public static boolean isValidNumber(String num) {
        return num.matches("-?\\d+");
    }
}
