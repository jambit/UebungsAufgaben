package com.jambit.azubis.javabasics;

import javax.naming.OperationNotSupportedException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public static final String EQUATION_REGEX_PATTERN = "^\\s*([-+]?\\d+\\.?\\d*)\\s*([-+*/]{1})\\s*([-+]?\\d+\\.?\\d*)\\s*$";
    public static final String QUIT_ACTION_CMD = "\\quit";

    private Scanner input;
    private final Pattern equationPattern;

    public Calculator() throws OperationNotSupportedException {
        input = new Scanner(System.in);
        equationPattern = Pattern.compile(EQUATION_REGEX_PATTERN);
        waitForInput();
    }

    public void waitForInput() throws OperationNotSupportedException {
        boolean run = true;
        while (run) {
            System.out.println("Enter the equation to be solved:\n");
            String inputStr = input.nextLine();
            if (inputStr.equalsIgnoreCase(QUIT_ACTION_CMD)) {
                run = false;
                continue;
            }
            Matcher matcher = equationPattern.matcher(inputStr);
            if (matcher.find()) {
                String operand_1 = matcher.group(1);
                String operator = matcher.group(2);
                String operand_2 = matcher.group(3);
                System.out.println("Calculating: " + operand_1 + " " + operator + " " + operand_2);
                float result = calculate(operator, operand_1, operand_2);
                System.out.println("Result: " + result);
            }
        }
    }

    private float calculate(String operator, String operand_1, String operand_2) throws OperationNotSupportedException {
        float op_1 = Float.parseFloat(operand_1);
        float op_2 = Float.parseFloat(operand_2);
        switch (operator) {
            case "+":
                return add(op_1, op_2);
            case "-":
                return subtract(op_1, op_2);
            case "*":
                return multipy(op_1, op_2);
            case "/":
                return devide(op_1, op_2);
            default:
                throw new OperationNotSupportedException("Operator " + operator + " not yet implemented");
        }
    }

    private float add(float op1, float op2) {
        return op1 + op2;
    }

    private float subtract(float op1, float op2) {
        return op1 - op2;
    }

    private float multipy(float op1, float op2){
        return op1 * op2;
    }

    private float devide(float op1, float op2){
        if(op2 == 0) {
            throw new IllegalArgumentException("devide with zero is not valid!");
        }
        return op1 / op2;
    }
}
