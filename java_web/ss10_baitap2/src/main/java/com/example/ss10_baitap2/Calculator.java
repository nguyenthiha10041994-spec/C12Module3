package com.example.ss10_baitap2;

public class Calculator {

    public static double calculate(double firstOperand, double secondOperand, String operator) throws Exception {

        switch (operator) {
            case "+":
                return firstOperand + secondOperand;

            case "-":
                return firstOperand - secondOperand;

            case "*":
                return firstOperand * secondOperand;

            case "/":
                if (secondOperand == 0) {
                    throw new Exception("Không thể chia cho 0");
                }
                return firstOperand / secondOperand;

            default:
                throw new Exception("Phép toán không hợp lệ");
        }
    }
}
