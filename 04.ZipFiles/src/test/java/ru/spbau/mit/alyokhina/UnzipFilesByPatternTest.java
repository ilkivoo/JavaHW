package ru.spbau.mit.alyokhina;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/** Test UnzipFilesByPattern */
public class UnzipFilesByPatternTest {

    /** In the test folder is stored the archive. It should unzip 2 files. After the test is complete, these files will be deleted */
    @Test
    public void testExtractFilesByPattern() throws Exception {
        UnzipFilesByPattern dir = new UnzipFilesByPattern();
        dir.extractFilesByPattern("./test", "^12.*");
        File file = new File("./test");
        File[] folderEntries = file.listFiles();
        boolean isExistFile1 = false;
        boolean isExistFile2 = false;
        for (File entry : folderEntries) {
            if (entry.getName().equals("12.txt")) {
                isExistFile1 = true;
            }
            if (entry.getName().equals("123.txt")) {
                isExistFile2 = true;
            }
        }
        new File("./test/12.txt").delete();
        new File("./test/123.txt").delete();
        assertEquals(true, isExistFile1 && isExistFile2);
    }

}