package ru.spbau.mit.alyokhina;

import org.junit.Test;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class CalculatorTest {
    @Test
    public void testGetRPN() {
        Stack<String> expression = mock(Stack.class);
        Stack<String> operand = mock(Stack.class);
        Stack<String> rpn = mock(Stack.class);
        when(expression.isEmpty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);

        when(expression.pop())
                .thenReturn("(")
                .thenReturn("1")
                .thenReturn("+")
                .thenReturn("3")
                .thenReturn(")")
                .thenReturn("*")
                .thenReturn("2");

        when(operand.isEmpty())
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true)
                .thenReturn(false)
                .thenReturn(true);

        when(operand.peek())
                .thenReturn("+")
                .thenReturn("(");

        when(operand.pop())
                .thenReturn("+")
                .thenReturn("(")
                .thenReturn("*");

        Calculator calculator = new Calculator(expression, operand, rpn);
        assertEquals(" 1 3 + 2 *", calculator.getRPN());


        verify(operand).push("(");
        verify(rpn).push("1");
        verify(operand).push("+");
        verify(rpn).push("3");
        verify(rpn).push("+");
        verify(operand).push("*");
        verify(rpn).push("2");
        verify(rpn).push("*");
    }

    @Test
    public void testCalculate() {
        Stack<String> expression = mock(Stack.class);
        Stack<String> operand = mock(Stack.class);
        Stack<String> rpn = mock(Stack.class);
        when(rpn.isEmpty())
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(true);
        when(rpn.peek())
                .thenReturn("*")
                .thenReturn("2")
                .thenReturn("+")
                .thenReturn("3")
                .thenReturn("1");

        when(rpn.pop())
                .thenReturn("*")
                .thenReturn("2")
                .thenReturn("+")
                .thenReturn("3")
                .thenReturn("1");
        Calculator calculator = new Calculator(expression, operand, rpn);
        assertEquals((Double) 8.0, calculator.calculate());

    }

}
