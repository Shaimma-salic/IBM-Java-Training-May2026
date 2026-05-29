package test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import main.LogFileAnalyzer;

class LogFileAnalyzerTest {

    private final Path outputFolder = Path.of("resources");
    private final Path actualSummary = Path.of("resources/summary.txt");
    private final PrintStream originalOutput = System.out;

    /**
     * Prepares the output folder before each test case.
     */
    @BeforeEach
    void setup() throws IOException {
        Files.createDirectories(outputFolder);

        if (Files.isDirectory(actualSummary)) {
            Files.delete(actualSummary);
        } else {
            Files.deleteIfExists(actualSummary);
        }
    }

    /**
     * Restores System.out and removes generated output after each test case.
     */
    @AfterEach
    void cleanup() throws IOException {
        System.setOut(originalOutput);

        if (Files.isDirectory(actualSummary)) {
            Files.delete(actualSummary);
        } else {
            Files.deleteIfExists(actualSummary);
        }
    }

    /**
     * Runs the main program and returns the printed console output.
     */
    String runProgram(String inputFile) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        LogFileAnalyzer.main(new String[] { inputFile });

        System.setOut(originalOutput);
        return output.toString();
    }

    /**
     * Should create the correct summary when all entries are valid.
     */
    @Test
    void exec001() throws IOException {
        String file = "src/test/resources/exec001/server.log";
        LogFileAnalyzer.main(new String[] { file });

        String actual   = Files.readString(actualSummary);
        String expected = Files.readString(Path.of("src/test/resources/exec001/summary.txt"));

        // Print both as escaped strings so you can see \r\n vs \n
        System.out.println("ACTUAL  : " + actual.replace("\r", "\\r").replace("\n", "\\n\n"));
        System.out.println("EXPECTED: " + expected.replace("\r", "\\r").replace("\n", "\\n\n"));

        assertEquals(expected, actual);
    }
    /**
     * Should skip a malformed entry with missing timestamp brackets.
     */
    @Test
    void exec002() throws IOException {
        String file = "src/test/resources/exec002/server.log";
        String expected = Files.readString(
                Path.of("src/test/resources/exec002/summary.txt"));

        String consoleOutput = runProgram(file);
        String actual = Files.readString(actualSummary);

        assertEquals(expected, actual);
        assertTrue(consoleOutput.contains(
                "Skipping malformed line: Invalid log entry"));
    }

    /**
     * Should skip an entry with an invalid log level.
     */
    @Test
    void exec003() throws IOException {
        String file = "src/test/resources/exec003/server.log";
        String expected = Files.readString(
                Path.of("src/test/resources/exec003/summary.txt"));

        String consoleOutput = runProgram(file);
        String actual = Files.readString(actualSummary);

        assertEquals(expected, actual);
        assertTrue(consoleOutput.contains(
                "Skipping malformed line: [2024-05-10 14:22:05] DEBUG: Testing program"));
    }

    /**
     * Should skip an entry that has no message.
     */
    @Test
    void exec004() throws IOException {
        String file = "src/test/resources/exec004/server.log";
        String expected = Files.readString(
                Path.of("src/test/resources/exec004/summary.txt"));

        String consoleOutput = runProgram(file);
        String actual = Files.readString(actualSummary);

        assertEquals(expected, actual);
        assertTrue(consoleOutput.contains(
                "Skipping malformed line: [2024-05-10 14:22:05] ERROR"));
    }

    /**
     * Should not create a summary file when the source log file is missing.
     */
    @Test
    void exec005() {
        String file = "src/test/resources/exec005/not-found.log";

        String consoleOutput = runProgram(file);

        assertTrue(consoleOutput.contains("Log file not found."));
        assertFalse(Files.exists(actualSummary));
    }

    /**
     * Should correctly update earliest and latest timestamps
     * even when log entries are not in time order.
     */
    @Test
    void exec006() throws IOException {
        String file = "src/test/resources/exec006/server.log";
        String expected = Files.readString(
                Path.of("src/test/resources/exec006/summary.txt"));

        String consoleOutput = runProgram(file);
        String actual = Files.readString(actualSummary);

        assertEquals(expected, actual);
        assertTrue(consoleOutput.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Should create a valid summary when the source log file is empty.
     */
    @Test
    void exec007() throws IOException {
        String file = "src/test/resources/exec007/server.log";
        String expected = Files.readString(
                Path.of("src/test/resources/exec007/summary.txt"));

        String consoleOutput = runProgram(file);
        String actual = Files.readString(actualSummary);

        assertEquals(expected, actual);
        assertTrue(consoleOutput.contains(
                "Analysis complete. Summary written to summary.txt"));
    }

    /**
     * Should print an error message when the summary output file
     * cannot be written.
     */
    @Test
    void exec008() throws IOException {
        String file = "src/test/resources/exec001/server.log";

        Files.createDirectory(actualSummary);

        String consoleOutput = runProgram(file);

        assertTrue(consoleOutput.contains("Error writing summary file."));
    }
}