package main.java.uk.ac.aber.cs21120.interfaces;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;

import java.util.Queue;

/**
 * The virtual machine which runs sequences of instructions (objects whose classes implement IInstruction).
 * These instructions manipulate a stack, which should be part of the implementing class.
 */

public interface IVirtualMachine {

    /**
     * Execute a queue of instructions (for tests)
     * @param queue
     */
    void execute(Queue<IInstruction> queue);

    /**
     * Execute a sequence of instructions, manipulating the machine's stack.
     * @param instructions instructions to run
     * @return
     */
    Double execute(String instructions);

    /**
     * Return the top value on the stack - WITHOUT removing it.
     * @return top value on stack
     */
    double getResult();

    /**
     * Reset the virtual machines
     */
    void reset();

}