package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class IOStreamUtils {

    public static void printProcessStdoutAndStderr(BufferedReader stdoutReader, BufferedReader stderrReader) {
        printProcessOutput(stdoutReader, System.out, "stdout");
        System.out.println();
        printProcessOutput(stderrReader, System.err, "stderr");
    }

    private static void printProcessOutput(BufferedReader br, PrintStream ps, String outputName) {
        String line;
        ps.println("** Printing '" + outputName + "' of the process **");
        try {
            while ((line = br.readLine()) != null) {
                ps.println(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
