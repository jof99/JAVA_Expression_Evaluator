package main.java.uk.ac.aber.cs21120.instruction.impl;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;

import java.util.Stack;

public class DivideInstruction implements IInstruction {
    @Override
    public char getOperator() {
        return '/';
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
//        if (a == 0) {
//            throw new UnsupportedOperationException("Cannot divide by zero");
//        }
        double result = (b / a);
        stack.push(result);
        System.out.println("Result of Divide " + b + "/" + a + "=" + result);
    }

    @Override
    public int getPrecedence() {
        return 1;
    }

    @Override
    public String toString() {
        return "DIVIDE";
    }

}
