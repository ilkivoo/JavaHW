package ru.spbau.mit.alyokhina;

import org.junit.Test;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        Pattern p = Pattern.compile("7 8 9 \\s4 5 6 \\s1 2 3");
        String testString = baos.toString().trim();
        Matcher m = p.matcher(testString);
        assertEquals(true, m.matches());
    }

    @Test
    public void print() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        spiral.print();
        Pattern p = Pattern.compile("1 2 3 \\s4 5 6 \\s7 8 9");
        String testString = baos.toString().trim();
        Matcher m = p.matcher(testString);
        assertEquals(true, m.matches());
    }

    @Test
    public void printSpiral() throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
        Spiral spiral = new Spiral(new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}});
        spiral.printSpiral();
        Pattern p = Pattern.compile("5\\s4\\s1\\s2\\s3\\s6\\s9\\s8\\s7");
        String testString = baos.toString().trim();
        Matcher m = p.matcher(testString);
        assertEquals(true, m.matches());
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