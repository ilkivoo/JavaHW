package ru.spbau.mit.alyokhina;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class SpiralTest {
    @Test
    public void testConstructor() throws Exception {
        Spiral matrix = new Spiral(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
    }

    @Test
    public void testConstructorNullMatrix() throws Exception {
        try {
            Spiral matrix = new Spiral(null);
            assertEquals("5", "6");
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testConstructorLenMatrixIsEven() throws Exception {
        try {
            Spiral matrix = new Spiral(new int[][]{{1, 2}, {4, 5}});
            assertEquals("5", "6");
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }


    @Test
    public void sort() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{9, 8, 7}, {6, 5, 4}, {3, 2, 1}});
        spiral.sort();
        spiral.print();
        assertEquals("7 8 9 \n4 5 6 \n1 2 3", baos.toString().trim());
    }

    @Test
    public void print() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        spiral.print();
        assertEquals("1 2 3 \n4 5 6 \n7 8 9", baos.toString().trim());
    }

    @Test
    public void printSpiral() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        spiral.printSpiral();
        assertEquals("5\n4\n1\n2\n3\n6\n9\n8\n7", baos.toString().trim());
    }

    @Test
    public void sortLenMatrixIsOne() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{9}});
        spiral.sort();
        spiral.print();
        assertEquals("9", baos.toString().trim());
    }

    @Test
    public void printLenMatrixIsOn() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{9}});
        spiral.print();
        assertEquals("9", baos.toString().trim());
    }

    @Test
    public void printSpiralLenMatrixIsOn() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{9}});
        spiral.printSpiral();
        assertEquals("9", baos.toString().trim());
    }

}