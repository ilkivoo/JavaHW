package ru.spbau.mit.alyokhina;

import org.junit.Test;
import com.google.common.collect.ImmutableMap;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import static org.junit.Assert.*;

public class SecondPartTasksTest {
    /** Creates new files. Records information in them. Call FindQuotes. When finished, deletes these files */
    @Test
    public void testFindQuotes() {
        File file1 = new File ("src/main/resources/file1.txt");
        File file2 = new File ("src/main/resources/file2.txt");
        File file3 = new File ("src/main/resources/file3.txt");
        if (!file1.exists()) {
            try {
                file1.createNewFile();
                file2.createNewFile();
                file3.createNewFile();
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        String s1 = "Since earliest boyhood he had doted\n" +
                "on Olga; from heart's ache still spared,\n" +
                "with tenderness he'd watched and noted\n" +
                "her girlhood games; in them he'd shared...";
        String s2 = "The reason was that there had \n" +
                "come into his life two friends of Schtoltz's, \n" +
                "in the shape of a pretty girl named Olga \n" +
                "Sergievna Ilyinitch and her aunt. On his \n" +
                "first visit to them he was overcome \n" +
                "with constraint.";
        String s3 = "Lots of chocolate for me to eat\n" +
                "Lots of coal makin' lots of heat\n" +
                "Warm face, warm hands, warm feet\n" +
                "Oh, wouldn't it be loverly?";
        try {
            FileWriter writer1 = new FileWriter(file1);
            FileWriter writer2 = new FileWriter(file2);
            FileWriter writer3 = new FileWriter(file3);
            writer1.write(s1);
            writer2.write(s2);
            writer3.write(s3);
            writer1.close();
            writer2.close();
            writer3.close();
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }

        assertEquals(Arrays.asList("on Olga; from heart's ache still spared,", "in the shape of a pretty girl named Olga "), SecondPartTasks.findQuotes(Arrays.asList("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/file3.txt"), "Olga"));
        assertEquals(Arrays.asList("Lots of chocolate for me to eat"), SecondPartTasks.findQuotes(Arrays.asList("src/main/resources/file1.txt", "src/main/resources/file2.txt", "src/main/resources/file3.txt"), "for me"));
        file1.delete();
        file2.delete();
        file3.delete();
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