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
        dir.extractFilesByPattern("./src/test/resources/forTest1", "^12.*");
        File file = new File("./src/test/resources/forTest1");
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
        new File("./src/test/resources/forTest1/12.txt").delete();
        new File("./src/test/resources/forTest1/123.txt").delete();
        assertEquals(true, isExistFile1 && isExistFile2);
    }

    /** Test ExtractFilesByPattern if dirictory is empty. Check that no files have been added and the directory is still empty */
    @Test
    public void testExtractFilesByPatternIfDirictoryIsEmpty() throws Exception {
        UnzipFilesByPattern dir = new UnzipFilesByPattern();
        dir.extractFilesByPattern("./src/test/resources/forTest2", "^12.*");
        File file = new File("./src/test/resources/forTest2");
        assertEquals(0, file.listFiles().length);
    }

    /** Test ExtractFilesByPattern if there are no zip-archives in the directory.*/
    @Test
    public void testExtractFilesByPatternIfNoZipArchives() throws Exception {
        UnzipFilesByPattern dir = new UnzipFilesByPattern();
        File file = new File("./src/test/resources/forTest3");
        int countFiles = file.listFiles().length;
        dir.extractFilesByPattern("./src/test/resources/forTest3", "^12.*");
        assertEquals(countFiles, file.listFiles().length);
    }

}