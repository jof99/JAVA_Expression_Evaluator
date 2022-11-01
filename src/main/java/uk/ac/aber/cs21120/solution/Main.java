package main.java.uk.ac.aber.cs21120.solution;

public class Main {
// Start of program
    public static void main(String[] args) {
        String expression = "(3*2)+(7*4)/2";
        VirtualMachine i = new VirtualMachine();
        i.execute(expression);
        System.out.println("Result: " + i.getResult());
    }

}
