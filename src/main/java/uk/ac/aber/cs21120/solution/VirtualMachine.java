package main.java.uk.ac.aber.cs21120.solution;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;
import main.java.uk.ac.aber.cs21120.interfaces.IVirtualMachine;
import main.java.uk.ac.aber.cs21120.interfaces.SyntaxException;

import java.util.*;

public class VirtualMachine implements IVirtualMachine {
    //initialises the parser object
    private Parser defaultParser;
    // initialises the operator and number stacks
    private Stack < Character > operatorStack;
    private Stack < Double > numberStack;

    public Parser getDefaultParser() {
        return defaultParser;
    }

    /**
     * Initialise virtual machine with custom parser
     */
    public VirtualMachine() {
        operatorStack = new Stack <> ();
        numberStack = new Stack <> ();
        defaultParser = new Parser(numberStack, operatorStack);
    }

    /**
     * clears the stack
     */
    @Override
    public void reset() {
        numberStack.clear();
        operatorStack.clear();
    }

    /**
     * Executes a que of instructions
     * @param queue takes a que
     */
    @Override
    public void execute(Queue<IInstruction> queue) {
        //If the queue is not empty, get the next instruction to execute
        while(!queue.isEmpty()) {
            try {
                IInstruction currentInstruction = queue.poll();
                currentInstruction.execute(numberStack);
            } catch (Exception e) {
                e.printStackTrace();;
            }
        }
    }

    /**
     * Takes a string of instructions to be executed through the parser
     * @param instructions instructions to run
     * @return returns the result
     */
    @Override
    public Double execute(String instructions) {
        Queue<IInstruction> instructionQueue;
        try {
            instructionQueue = defaultParser.parseExpression(instructions);
        } catch (SyntaxException e) {
            throw new IllegalStateException("Syntax error");
        }
        System.out.println("Executing " + instructionQueue.size() + " instructions [" + instructions + "]");
        execute(instructionQueue);
        return getResult();
    }

    /**
     * Get the instructions at the top of the stack
     * @return the values at the top of the stack
     */
    @Override
    public double getResult() {
        return numberStack.peek();
    }

}