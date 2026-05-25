package LogFileAnalyzer;
import java.io.*;
import java.util.*;
import java.time.*;

public class SummaryWriter {

    public static void write(
            int totalEntries,
            Map<String, Integer> levelCounts,
            List<String> errorMessages,
            LocalDateTime earliest,
            LocalDateTime latest) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("summary.txt"))) {

            bw.write("Log Summary Report");
            bw.newLine();
            bw.write("------------------");
            bw.newLine();

            bw.write("Total Entries: " + totalEntries);
            bw.newLine();

            bw.write("INFO: " + levelCounts.get("INFO"));
            bw.newLine();

            bw.write("WARN: " + levelCounts.get("WARN"));
            bw.newLine();

            bw.write("ERROR: " + levelCounts.get("ERROR"));
            bw.newLine();
            bw.newLine();

            bw.write("Error Messages:");
            bw.newLine();

            for (String message : errorMessages) {
                bw.write("- " + message);
                bw.newLine();
            }

            bw.newLine();

            if (earliest != null && latest != null) {
                bw.write("Earliest Timestamp: " +
                        earliest.format(LogEntryParser.getFormatter()));
                bw.newLine();

                bw.write("Latest Timestamp: " +
                        latest.format(LogEntryParser.getFormatter()));
            }

        } catch (IOException e) {
            System.out.println("Error writing summary file: " + e.getMessage());
        }
    }
}
