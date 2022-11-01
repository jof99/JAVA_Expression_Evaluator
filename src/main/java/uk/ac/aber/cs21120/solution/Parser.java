package main.java.uk.ac.aber.cs21120.solution;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;
import main.java.uk.ac.aber.cs21120.instruction.NumberInstruction;
import main.java.uk.ac.aber.cs21120.instruction.impl.*;
import main.java.uk.ac.aber.cs21120.interfaces.IParser;
import main.java.uk.ac.aber.cs21120.interfaces.SyntaxException;

import java.util.*;

public class Parser implements IParser {
    // instance variables of the stack data structures
    private Stack < Character > operatorStack;
    private Stack < Double > numbersStack;


    List<IInstruction> supportedInstructions = new ArrayList<>();

    /**
     * This initializes the parser
     * @param numbers Takes a stack of integers
     * @param operators Takes a stack of operators
     */
    public Parser(Stack< Double > numbers, Stack < Character > operators) {
        this.operatorStack = operators;
        this.numbersStack = numbers;
        // add list of supported instructions
        supportedInstructions.add(new AddInstruction());
        supportedInstructions.add(new DivideInstruction());
        supportedInstructions.add(new MultiplyInstruction());
        supportedInstructions.add(new PowerOfInstruction());
        supportedInstructions.add(new SubtractInstruction());
    }

    /**
     * Checks if a character is a mathematical operator.
     * @param c Is the operator being passed = a supported opperation
     * @return returns an operation
     */
    public boolean isAnOperator(char c) {
        return (c == '^' || c == '*' || c == '+' || c == '-' || c == '/');
    }

    /**
     * Get instruction to execute based on operator.
     * @param operator a mathematical operation
     * @return returns an instruction
     */
    public IInstruction getInstruction(char operator) {
        for(IInstruction instruction : supportedInstructions) {
            if(instruction.getOperator() == operator) {
                return instruction;
            }
        }
        return null;
    }

    /**
     * Get precedence based on operator
     * @param operator takes an operator and passes it to an instruction
     * @return returns the precedence of the instruction
     */
    public int getPrecedence(char operator) {
        for(IInstruction instruction : supportedInstructions) {
            if(instruction.getOperator() == operator) {
                return instruction.getPrecedence();
            }
        }
        return - 1;
    }

    /**
     * Add the next instruction to the queue based on the operator
     * @param queue
     */
    public void addInstructionForOperator(Queue<IInstruction> queue) {
        // getting the operator
        char operator = operatorStack.pop();
        // add instruction to queue based on operator
        IInstruction instructionToExecute = getInstruction(operator);
        if(instructionToExecute == null) {
            throw new IllegalStateException("Invalid operator '" + operator + "'");
        }
        queue.add(instructionToExecute);
    }

    /**
     * Parsing instructions using tokenizer
     * @param instructions
     * @return
     */
    public  Queue<IInstruction> tokenizerAttempt(String instructions) {
        Queue<IInstruction> queue = new LinkedList<>();
        System.out.println("Attempting to tokenize " + instructions);
        StringTokenizer tokenizer = new StringTokenizer(instructions,"()*/-+", true);
        while(tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            char currentCharacter = token.charAt(0);

            // if character is digit
            if (Character.isDigit(currentCharacter)) {
                double number = 0;
                String out = "";
                if (Character.isDigit(currentCharacter)) {
                    out = "" + currentCharacter + "";
                    while(tokenizer.hasMoreTokens()) {
                        currentCharacter = tokenizer.nextToken().charAt(0);
                        if (Character.isDigit(currentCharacter)) {
                            out += currentCharacter;
                        } else {
                            break;
                        }
                    }
                }
                queue.add(new NumberInstruction(Double.parseDouble(out)));
            } else if (currentCharacter == '(') {
                operatorStack.push(currentCharacter);
                // With a closing bracket, everything inside the opening and closing brackets are evaluated
            } else if (currentCharacter == ')') {
                while (operatorStack.peek() != '(') {
                    addInstructionForOperator(queue);
                }
                operatorStack.pop();
            } else if (isAnOperator(currentCharacter)) {
                while (!operatorStack.isEmpty() && getPrecedence(currentCharacter) <= getPrecedence(operatorStack.peek())) {
                    addInstructionForOperator(queue);
                }
                operatorStack.push(currentCharacter);
            }
        }

        while (!operatorStack.isEmpty()) {
            addInstructionForOperator(queue);
        }

        return queue;
    }

    /**
     * This method parses a string on instructions
     * @param instructions a string
     * @return Returns a queue of instructions to execute
     * @throws SyntaxException Throws a syntax errors if there are errors in the expressions syntax
     */

    @Override
    public Queue<IInstruction> parseExpression(String instructions) throws SyntaxException {
        boolean testTokenizer = false;
        if(testTokenizer) {
           return tokenizerAttempt(instructions);
        }

        // instruction stack sorted by precedence
        Queue<IInstruction> queue = new LinkedList<>();
        // Loop through the instruction sequence character by character
        for (int i = 0; i < instructions.length(); i++) {
            char currentCharacter = instructions.charAt(i);
            // At each character it checks wether the character is a digit or not
            if (Character.isDigit(currentCharacter)) {
                double number = 0;
                while (Character.isDigit(currentCharacter)) {
                    number = number * 10 + (currentCharacter - '0');
                    i++;
                    if (i < instructions.length())
                        currentCharacter = instructions.charAt(i);
                    else
                        break;
                }
                i--;
                queue.add(new NumberInstruction(number));
                //If the digit is a bracket it handles it differently. It pushes it to the top of the stack
            } else if (currentCharacter == '(') {
                operatorStack.push(currentCharacter);
                // With a closing bracket, everything inside the opening and closing brackets are evaluated
            } else if (currentCharacter == ')') {
                while (operatorStack.peek() != '(') {
                    addInstructionForOperator(queue);
                }
                operatorStack.pop();
            } else if (isAnOperator(currentCharacter)) {
                while (!operatorStack.isEmpty() && getPrecedence(currentCharacter) <= getPrecedence(operatorStack.peek())) {
                    addInstructionForOperator(queue);
                }
                operatorStack.push(currentCharacter);
            }
        }
        //if the operator stack is not empty then an instruction can be added to the que
        while (!operatorStack.isEmpty()) {
            addInstructionForOperator(queue);
        }
        return queue;
    }

}
