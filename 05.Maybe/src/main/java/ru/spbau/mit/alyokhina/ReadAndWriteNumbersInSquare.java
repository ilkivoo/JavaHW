package ru.spbau.mit.alyokhina;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Class for working with files
 * Read data from file
 * Write numbers in square
 */
public class ReadAndWriteNumbersInSquare {

    /**
     * Read the numbers from the file: if there is a number in the string, "Maybe" is returned with a number,
     * if the number is not written in the line, "Maybe" is returned with null.
     * @param inputFileName name file for read
     */

    public static ArrayList<Maybe<Integer>> read(String inputFileName) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader(new File(inputFileName)));
        ArrayList<Maybe<Integer>> result = new ArrayList<>();
        while(scanner.hasNext()) {
            Maybe<Integer> maybe;
            if (scanner.hasNextInt()) {
                maybe = Maybe.just(scanner.nextInt());
            } else {
                scanner.next();
                maybe = Maybe.nothing();
            }
            result.add(maybe);
        }
        return result;
    }

    /** Save the Maybe array to a new file. Write "null" to the new file, if there was not a number in the source file on this line.
     * @param outputFileName name file for write
     * @param data array Maybe
     */
    public static void write(String outputFileName, ArrayList<Maybe<Integer>> data) throws IOException, ValueNotPresentException {
        PrintWriter printer = new PrintWriter(new FileWriter(new File(outputFileName)));
        for (Maybe<Integer> maybe : data) {
            maybe = maybe.map(x -> x * x);
            if (maybe.isPresent()) {
                printer.println(maybe.get());
            } else {
                printer.println("null");
            }
        }
        printer.close();
    }
}
