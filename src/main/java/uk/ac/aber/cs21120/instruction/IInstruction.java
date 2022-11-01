package main.java.uk.ac.aber.cs21120.instruction;

import java.util.Stack;

/**
 * This interface should be implemented by all instruction classes. It describes an
 * instruction which operates on a stack of double-precision floating point values.
 * The most important method is execute(), which actually performs the instruction.
 * The getPrecedence() method is used during expression compilation, and not when
 * the instruction is run.
 */
public interface IInstruction {

    /**
     * Get the operator for the instruction
     * @return
     */
    char getOperator();

    /**
     * Execute the instruction, making changes to the stack.
     * @param stack the stack to manipulate.
     */
    void execute(Stack<Double> stack);

    /**
     * Return the precedence of the instruction if it is an operator - if not, just return zero
     * (this will be ignored).
     * @return precedence: more "tightly-binding" operators should return higher values. For example, "multiply"
     * should be higher than "add"
     */
    int getPrecedence();

    /**
     * Return a string representation of the instruction for debugging purposes.
     * @return a string
     */
    String toString();
}
