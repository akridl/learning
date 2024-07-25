package com.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {
        // listHomeDirectory();
        // gitStatus(Path.of("/home", "akridl", "repos", "learning", "native-processes"));
        runScript();
    }

    private static void listHomeDirectory() {
        ProcessBuilder pb = new ProcessBuilder(List.of("ls", "-la"))
                .directory(new File("/home/akridl"));
        startProcessAndPrintStdoutWithStderr(pb);
    }

    private static void gitStatus(Path workingDirectory) {
        ProcessBuilder pb = new ProcessBuilder(List.of("git", "status"));

        if (!Files.isDirectory(workingDirectory)) {
            throw new RuntimeException("Provided working directory is not a directory");
        }
        pb.directory(workingDirectory.toFile());
        startProcessAndPrintStdoutWithStderr(pb);
    }

    private static void runScript() {
        ProcessBuilder pb = new ProcessBuilder("./script.sh")
                .directory(Path.of("./src", "main", "resources").toFile());
        Map<String, String> env = pb.environment();
        env.clear();
        env.put("FOO", "42");

        startProcessAndPrintStdoutWithStderr(pb);
    }

    private static void startProcessAndPrintStdoutWithStderr(ProcessBuilder pb) {
        try {
            Process p = pb.start();
            IOStreamUtils.printProcessStdoutAndStderr(p.inputReader(), p.errorReader());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
