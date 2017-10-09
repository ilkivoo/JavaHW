package ru.spbau.mit.alyokhina;

import java.io.*;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class UnzipFilesByPattern {
   /** Regular expression on which we want to find files*/
   private String regex;

    /**
     * Find zip-archive
     * @param folder directory in which the search will be
     */
   private void findArchives(File folder) {
        File[] folderEntries = folder.listFiles();
        for (File entry : folderEntries) {
            if (entry.isDirectory()) {
                findArchives(entry);
            }
            else {
                if (entry.toString().endsWith(".zip")) {
                    findFilesFromZipArchiveByPattern(entry);
                }
            }
        }
   }

    /**
     * Get name file
     * @param path absolute path
     * @return name file
     */
   private String getShortName(String path) {
       String shortName = "";
       int i = path.length() - 1;
       while (i >= 0 && path.charAt(i) != '/') {
           shortName = path.charAt(i) + shortName;
           i--;
       }
       return shortName;
   }

    /**
     * Find files that match a regular expression from zip-archive
     * @param zipArchive archive in which the search will be executed
     */
    private void findFilesFromZipArchiveByPattern(File zipArchive) {
        try {
            ZipFile zip = new ZipFile(zipArchive.getAbsolutePath());
            Enumeration entries = zip.entries();

            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                Pattern pattern = Pattern.compile(regex);
                Matcher m = pattern.matcher(getShortName(entry.getName()));
                if (!entry.isDirectory() && m.matches()) {
                    write(zip.getInputStream(entry), new BufferedOutputStream(new FileOutputStream(new File(zipArchive.getParent(), getShortName(entry.getName())))));
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * write from one stream to another
     * @param in stream from which we will write down
     * @param out stream into which we will write
     */
    private static void write(InputStream in, OutputStream out) {
        byte[] buffer = new byte[1024];
        int len;
        try {
            while ((len = in.read(buffer)) >= 0)
                out.write(buffer, 0, len);
            out.close();
            in.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** On the specified path, it searches for all zip files and extracts all the files that will satisfy the regular expression. */
    public void extractFilesByPattern(String path, String regexExpression) {
       regex = regexExpression;
       findArchives(new File(path));
    }
}
