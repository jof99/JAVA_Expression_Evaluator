package main.java.uk.ac.aber.cs21120.interfaces;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;

import java.util.Queue;

/**
 * A class which implements this interface takes a string written in infix notation (e.g. "3+4*2")
 * and converts it into a queue of instructions conforming to the IInstruction interface. These instructions
 * will perform the operations represented by the original string in the correct order and with the correct
 * precedence. For example, "3+4*2" will be converted to the sequence
 *  NUMBER-3
 *  NUMBER-4
 *  NUMBER-2
 *  MULTIPLY
 *  ADD
 * (where these strings are the toString() representations of the IInstruction objects).
 */
public interface IParser {
    /**
     * Given a string containing a mathematical expression in infix notation, convert it into
     * a sequence of IInstruction objects performing that expression.
     * @param s the string to convert
     * @return a sequence of instructions
     * @throws SyntaxException
     */
    Queue<IInstruction> parseExpression(String s) throws SyntaxException;
}

