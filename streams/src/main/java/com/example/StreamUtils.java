package com.example;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.Reader;
import java.io.Writer;

public class StreamUtils {

    public static void print(InputStream is) {
        try {
            int b;
            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }
            System.out.println();
        } catch (IOException e) {
            throw new AppException("IOException occurred while reading characters from input stream " + is, e);
        }
    }

    public static void printLines(BufferedReader br, PrintStream ps) {
        String line;
        try {
            while ((line = br.readLine()) != null) {
                ps.print("=== ");
                ps.println(line);
            }
        } catch (IOException e) {
            throw new AppException("IOException occurred while reading lines from reader " + br, e);
        }
    }

    public static void readCharacterStream(Reader r) {
        try {
            int b;
            while ((b = r.read()) != -1) {
                System.out.print((char) b);
            }
        } catch (IOException e) {
            throw new AppException("IOException occurred while reading characters from reader " + r, e);
        }
    }

    public static void copy(InputStream is, OutputStream os) {
        try {
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }
            os.flush();
        } catch (IOException e) {
            throw new AppException("IOException occurred while reading characters from input stream " + is + " to output stream " + os, e);
        }
    }

    public static void copy(Reader r, Writer w) {
        try {
            int b;
            while ((b = r.read()) != -1) {
                w.write(b);
            }
            w.flush();
        } catch (IOException e) {
            throw new AppException("IOException occurred while copying characters from reader " + r + " to writer " + w, e);
        }
    }
}
