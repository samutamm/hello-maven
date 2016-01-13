package com.samutamm;

import org.apache.maven.plugin.logging.Log;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;


public class FilePrinter {
    private Log log;

    public FilePrinter(Log log) {
        this.log = log;
    }

    public void printFileNames(String root) {
        final int foldersInRoot = root.split("/").length;
        log.info("");
        try {
            Files.walk(Paths.get(root))
                    .map((path) -> alignRows(foldersInRoot, path))
                    .forEach(log::info);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String alignRows(int foldersInRoot, Path path) {
        int folders = path.toString().split("/").length;
        int caps = folders - foldersInRoot;
        String fileRow = path.getFileName().toString();
        for(int i = 0; i < caps; i++) {
            fileRow = "    " + fileRow;
        }
        return fileRow;
    }

    public void printTodoRows(String root, Integer rowInt) {
        try {
            Files.walk(Paths.get(root))
                    .filter((path) -> path.toFile().isFile())
                    .forEach((path) -> readAndPrintRows(path, rowInt));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndPrintRows(Path path, Integer rows) {
        String query = "// TODO";
        try {
            boolean match = Files.lines(path)
                    .anyMatch((line) -> line.contains(query));
            if (match) {
                log.info("    " + path.getFileName());
                handleLines(Files.lines(path).toArray(), query, rows);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleLines(Object[] lines, String query, Integer rows) {
        for (int i = 0; i < lines.length; i++) {
            if (lines[i].toString().contains(query)) {
                for(int untilRows = 0; untilRows < rows && i+untilRows < lines.length; untilRows++) {
                    int printIndex = i + untilRows;
                    log.info(printIndex + "   " + lines[printIndex].toString());
                }
                log.info("");
            }
        }
    }
}
