package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.io.SequenceInputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

public class App {

    private static final File utf8ShortFile = new File("src/main/resources/utf8-short.txt");
    private static final File utf8ShortFile2 = new File("src/main/resources/utf8-short2.txt");
    private static final File utf8ShortFileCopy = new File("src/main/resources/utf8-short-copy.txt");
    private static final File utf8MediumFile = new File("src/main/resources/utf8-medium.txt");
    private static final File iso8859File = new File("src/main/resources/iso8859-short-copy.txt");

    public static void main(String[] args) {
        // standardInputStream();
        readFileNotBuffered(utf8ShortFileCopy);
        copyFileNotBuffered(utf8ShortFile, utf8ShortFileCopy);
        readFileBuffered(utf8ShortFileCopy);
        readTwoFilesBuffered(utf8ShortFileCopy, utf8ShortFile2);
        readFileViaReaderExplicit(utf8ShortFile);
        readFileViaReader(utf8ShortFile);
        copyUtf8FileToIso8859(utf8ShortFile2, iso8859File);
        readLines(utf8MediumFile);
    }

    private static void standardInputStream() throws IOException {
        System.out.print("Enter a character: ");
        int c = System.in.read();
        System.out.println("The character you entered is: " + (char) c);
    }

    private static void readFileNotBuffered(File file) {
        try (InputStream fis = new FileInputStream(file)) {
            StreamUtils.print(fis);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void readFileBuffered(File file) {
        try (InputStream bis = new BufferedInputStream(new FileInputStream(file))) {
            StreamUtils.print(bis);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void readFileViaReaderExplicit(File file) {
        try (Reader r = new InputStreamReader(new FileInputStream(file))) {
            StreamUtils.readCharacterStream(r);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void readFileViaReader(File file) {
        try (Reader r = new FileReader(file)) {
            StreamUtils.readCharacterStream(r);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void readTwoFilesBuffered(File file1, File file2) {
        try (
                InputStream fis1 = new BufferedInputStream(new FileInputStream(file1));
                InputStream fis2 = new BufferedInputStream(new FileInputStream(file2));
        ) {
            InputStream sequenceInputStream = new SequenceInputStream(fis1, fis2);
            StreamUtils.print(sequenceInputStream);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void readLines(File file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            StreamUtils.printLines(br, System.out);
        } catch (FileNotFoundException e) {
            throw new AppException("File not found", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void copyFileNotBuffered(File sourceFile, File destinationFile) {
        try (
                InputStream fis = new FileInputStream(sourceFile);
                OutputStream fos = new FileOutputStream(destinationFile)
        ) {
            StreamUtils.copy(fis, fos);
        } catch (FileNotFoundException e) {
            throw new AppException("Cannot find file", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }

    private static void copyUtf8FileToIso8859(File sourceFile, File destinationFile) {
        try (
             Reader r = new FileReader(sourceFile);
             Writer w = new FileWriter(destinationFile, StandardCharsets.ISO_8859_1)
        ) {
            StreamUtils.copy(r, w);
        } catch (FileNotFoundException e) {
            throw new AppException("Cannot find file", e);
        } catch (IOException e) {
            throw new AppException("Unexpected IO exception", e);
        }
    }
}
