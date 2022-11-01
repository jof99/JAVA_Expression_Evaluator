package uk.ac.aber.cs21120.calc.tests;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;
import main.java.uk.ac.aber.cs21120.instruction.NumberInstruction;
import main.java.uk.ac.aber.cs21120.instruction.impl.AddInstruction;
import main.java.uk.ac.aber.cs21120.instruction.impl.DivideInstruction;
import main.java.uk.ac.aber.cs21120.instruction.impl.MultiplyInstruction;
import main.java.uk.ac.aber.cs21120.instruction.impl.SubtractInstruction;
import main.java.uk.ac.aber.cs21120.interfaces.IVirtualMachine;
import main.java.uk.ac.aber.cs21120.solution.VirtualMachine;
import org.junit.Test;
import org.junit.Assert;
import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

public class InstructionTests {

    static Queue<IInstruction> generate(IInstruction... args) {
        int idx = 0;
        Queue<IInstruction> queue = new LinkedList<>();
        for(IInstruction i: args){
            queue.add(i);
        }
        return queue;
    }

    @Test
    public void testExpression() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute("1+1");
        Assert.assertEquals(2, vm.getResult(), 0);
    }

    @Test
    public void bracketsTest() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute("1*(5+5)/2");
        Assert.assertEquals(5, vm.getResult(), 0);
        System.out.println("Bracket test passed!");
    }

    @Test
    public void testNumber() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(new NumberInstruction(10)));
        Assert.assertEquals(10, vm.getResult(), 0);
        vm.execute(generate(new NumberInstruction(20)));
        Assert.assertEquals(20, vm.getResult(), 0);
        System.out.println("Number test passed!");
    }

    @Test
    public void testAdd() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(
                new NumberInstruction(10.6),
                new NumberInstruction(15.1),
                new AddInstruction()));
        Assert.assertEquals(25.7, vm.getResult(), 0);
        System.out.println("Addition test passed!");
    }

    @Test
    public void testMultiply() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(
                new NumberInstruction(7.5),
                new NumberInstruction(6.5),
                new MultiplyInstruction()
        ));
        Assert.assertEquals(48.75, vm.getResult(), 0);
        System.out.println("Multiplication test passed!");
    }

    @Test
    public void testSubtract() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(
                new NumberInstruction(7.25),
                new NumberInstruction(16.75),
                new SubtractInstruction()
        ));
        Assert.assertEquals(-9.5, vm.getResult(), 0);
        System.out.println("Subtract test passed!");
    }

    @Test
    public void testDivide() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(
                new NumberInstruction(17.0),
                new NumberInstruction(2.5),
                new DivideInstruction()
        ));
        Assert.assertEquals(6.8, vm.getResult(), 0);
        System.out.println("Divide test passed!");
    }

    @Test
    public void testDivideZero() {
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(
                new NumberInstruction(17.0),
                new NumberInstruction(0.0),
                new DivideInstruction()
        ));
        Assert.assertEquals(Double.POSITIVE_INFINITY, vm.getResult(), 0);
        System.out.println("Divide by zero test passed!");
    }

    @Test
    public void testEmptyStack() {
        try {
            IVirtualMachine vm = new VirtualMachine();
            vm.execute(generate(new AddInstruction()));
        } catch(Exception e) {
            System.out.println("Empty stack test passed!");
        }
    }

    @Test
    public void testCompound() {
        IVirtualMachine vm = new VirtualMachine();

        vm.execute(generate(
                new NumberInstruction(18.0),
                new NumberInstruction(3.0),
                new DivideInstruction(),
                new NumberInstruction(31.0),
                new AddInstruction(),
                new NumberInstruction(16.0),
                new SubtractInstruction(),
                new NumberInstruction(11.0),
                new MultiplyInstruction()
        ));
        Assert.assertEquals(231, vm.getResult(), 0);
        System.out.println("Compound test passed!");
    }

    @Test
    public void testMultiResultGet(){
        IVirtualMachine vm = new VirtualMachine();
        vm.execute(generate(new NumberInstruction(10)));
        Assert.assertEquals(10, vm.getResult(), 0);
        Assert.assertEquals(10, vm.getResult(), 0);
        Assert.assertEquals(10, vm.getResult(), 0);
        System.out.println("Multi get test passed!");
    }


}
