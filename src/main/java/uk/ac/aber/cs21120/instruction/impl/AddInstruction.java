package main.java.uk.ac.aber.cs21120.instruction.impl;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;

import java.util.Stack;

public class AddInstruction implements IInstruction {
    /**
     * This method gets the operator
     * @return returns a plus
     */
    @Override
    public char getOperator() {
        return '+';
    }

    /**
     * Pushes result of calculation to the stack for the next instruction to use, or if this is
     * the last instruction then this will be the result.
     * @param stack the stack to manipulate.
     */
    @Override
    public void execute(Stack<Double> stack) {

        Double a = stack.pop();
        Double b = stack.pop();
        double result = (a + b);
        stack.push(result);
        System.out.println("Result of Add " + a + "+" + b + "=" + result);
    }

    @Override
    public int getPrecedence() {
        return 3;
    }

    @Override
    public String toString() {
        return "ADD";
    }

}