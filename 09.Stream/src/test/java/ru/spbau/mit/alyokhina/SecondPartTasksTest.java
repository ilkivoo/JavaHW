package ru.spbau.mit.alyokhina;

import org.junit.Test;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static org.junit.Assert.*;

public class SecondPartTasksTest {
    /**
     * File for test store in src/main/resources. Calls FindQuotes
     */
    @Test
    public void testFindQuotes() {
        assertEquals(Arrays.asList("on Olga; from heart's ache still spared,", "in the shape of a pretty girl named Olga "), SecondPartTasks.findQuotes(Arrays.asList("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/file3.txt"), "Olga"));
        assertEquals(Arrays.asList("Lots of chocolate for me to eat"), SecondPartTasks.findQuotes(Arrays.asList("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/file3.txt"), "for me"));
    }

    @Test
    public void testPiDividedBy4() {
        assertEquals(Math.PI / 4, SecondPartTasks.piDividedBy4(), 0.01);
    }

    @Test
    public void testFindPrinter() {
        assertEquals("Leo Tolstoy", SecondPartTasks.findPrinter(ImmutableMap.of(
                "Alexander Pushkin", Arrays.asList("Boris Godunov", "Ruslan i Ludmila"),
                "Leo Tolstoy", Arrays.asList("The Cossacks", "War and Peace", "Anna Karenina", "Resurrection!"),
                "Ivan Bunin", Arrays.asList("The Gentleman from San Francisco ", "Sunstroke")
                ))
        );
    }

    @Test
    public void testCalculateGlobalOrder() {
        assertEquals(ImmutableMap.of("Ice cream", 3, "Java", 8, "Chocolate", 15), SecondPartTasks.calculateGlobalOrder(Arrays.asList(
                ImmutableMap.of("Chocolate", 3, "Ice cream", 2),
                ImmutableMap.of("Chocolate", 12, "Java", 5, "Ice cream", 1),
                ImmutableMap.of("Java", 3)
                ))
        );
    }
}