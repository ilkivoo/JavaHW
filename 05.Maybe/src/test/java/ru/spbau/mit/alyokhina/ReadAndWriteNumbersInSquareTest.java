package ru.spbau.mit.alyokhina;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import static org.junit.Assert.*;

/** Test for ReadAndWriteNumbersInSquare */
public class ReadAndWriteNumbersInSquareTest {

    /** Create file with data. Verifies that the function Read returns the correct array */
    @Test
    public void testRead() throws Exception {
        File file = new File("1.txt");
        file.createNewFile();
        PrintWriter printer = new PrintWriter(file);
        printer.println("olya");
        printer.println("4");
        printer.println("lalala");
        printer.println("5");
        printer.print("7");
        printer.close();
        ArrayList<Maybe<Integer>> arr = ReadAndWriteNumbersInSquare.read("1.txt");
        assertEquals(false, arr.get(0).isPresent());
        assertEquals(true, arr.get(1).isPresent());
        assertEquals(false, arr.get(2).isPresent());
        assertEquals(true, arr.get(3).isPresent());
        assertEquals(true, arr.get(4).isPresent());
        assertEquals((Integer) 4, arr.get(1).get());
        assertEquals((Integer) 5, arr.get(3).get());
        assertEquals((Integer) 7, arr.get(4).get());
        file.deleteOnExit();
    }

    /** Create 2 files. In one of them, the data.
     *  In the other, the squares of all numbers are recorded. It is checked that all the data output is correct
     */
    @Test
    public void testWrite() throws Exception {
        File file = new File("1.txt");
        file.createNewFile();
        PrintWriter printer = new PrintWriter(file);
        printer.println("olya");
        printer.println("4");
        printer.println("lalala");
        printer.println("5");
        printer.print("7");
        printer.close();
        File file2 = new File ("2.txt");
        ReadAndWriteNumbersInSquare.write("2.txt", ReadAndWriteNumbersInSquare.read("1.txt"));
        Scanner scan = new Scanner(file2);
        assertEquals("null", scan.next());
        assertEquals("16", scan.next());
        assertEquals("null", scan.next());
        assertEquals("25", scan.next());
        assertEquals("49", scan.next());
        file.deleteOnExit();
        file2.deleteOnExit();
    }

}