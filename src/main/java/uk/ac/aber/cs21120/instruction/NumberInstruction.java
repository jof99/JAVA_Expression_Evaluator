package main.java.uk.ac.aber.cs21120.instruction;

import java.util.Stack;

public class NumberInstruction implements IInstruction {

    private double value;

    /**
     * initialising the value to be pushed to the stack
     * @param value
     */
    public NumberInstruction(double value) {
        this.value = value;
    }

    public double getValue() {
        return value;
    }

    @Override
    public char getOperator() {
        return 0;
    }

    /**
     * pushes value to the stack
     * @param stack the stack to manipulate.
     */
    @Override
    public void execute(Stack<Double> stack) {
        stack.push(value);
    }

    @Override
    public int getPrecedence() {
        return 0;
    }

    /**
     * Overrides the to string method so instead of showing class name it shows something recognisable
     * @return number of instruction
     */
    @Override
    public String toString() {
        return "Number-" + Double.toString(value);
    }

}