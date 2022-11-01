package main.java.uk.ac.aber.cs21120.interfaces;

/**
 * This exception is thrown by the parseExpression method of IParser-conforming classes.
 * It's thrown when the parser detects a problem.
 */
public class SyntaxException extends Exception {
    public SyntaxException(String s){
        super(s);
    }


}