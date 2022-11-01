package uk.ac.aber.cs21120.calc.tests;

import main.java.uk.ac.aber.cs21120.instruction.IInstruction;
import main.java.uk.ac.aber.cs21120.interfaces.IParser;
import main.java.uk.ac.aber.cs21120.interfaces.SyntaxException;
import main.java.uk.ac.aber.cs21120.solution.Parser;
import main.java.uk.ac.aber.cs21120.solution.VirtualMachine;
import org.junit.Assert;
import org.junit.Test;

import java.util.Queue;
import java.util.Stack;

public class ParserTests {

    // call this if you want syntax exceptions converted to runtime exceptions..
    private double test(String s){
        try {
            return testBase(s);
        } catch (SyntaxException e) {
            e.printStackTrace();
            throw new RuntimeException("Test failed: "+e.getMessage());
        }
    }

    // and this if you don't (so we can check that SyntaxException is thrown)

    private double testBase(String s) throws SyntaxException {
        IParser p = new Parser(new Stack<Double>(), new Stack<Character>());
        Queue<IInstruction> r = p.parseExpression(s);
        VirtualMachine vm = new VirtualMachine();
        vm.execute(r);
        double res = vm.getResult();
        System.out.println("Test done, result "+Double.toString(res));
        return res;
    }

    @Test
    public void simpleSame(){
        Assert.assertEquals(20,test("10+10"), 0);
    }

    @Test
    public void simpleDiff(){
        Assert.assertEquals(20,test("15+5"), 0);
    }

    @Test
    public void add3(){
        Assert.assertEquals(20,test("5+5+10"), 0);
    }

    @Test
    public void add4(){
        Assert.assertEquals(10,test("1+2+3+4"), 0);
    }

    @Test
    public void addMul1(){
        Assert.assertEquals(20,test("5*2+10"), 0);
    }

    @Test
    public void addMul2(){
        Assert.assertEquals(20,test("10+5*2"), 0);
    }

    @Test
    public void addMul3(){
        Assert.assertEquals(21,test("3+2*7+4"), 0);
    }

    @Test
    public void addMul4(){
        Assert.assertEquals(34,test("3*2+7*4"), 0);
    }

    @Test
    public void subDiv1(){
        Assert.assertEquals(7.5,test("10-5/2"), 0);
    }

    @Test
    public void subDiv2(){
        Assert.assertEquals(0.0,test("10/5-2"), 0);
    }

    @Test
    public void combo(){
        Assert.assertEquals(12,test("3+7*2-5"), 0);
    }

    @Test
    public void addMulBracket1(){
        Assert.assertEquals(60,test("5*(2+10)"), 0);
    }

    @Test
    public void addMulBracket2(){
        Assert.assertEquals(30,test("(10+5)*2"), 0);
    }

    @Test
    public void comboBracket1(){
        Assert.assertEquals(-3,test("(1+2)*(3-4)"), 0);
    }


    @Test
    public void syntax1(){
        try {
            testBase("this string is garbage");
        } catch (Exception e) {
            System.out.println("Syntax test passed");
        }
    }


}