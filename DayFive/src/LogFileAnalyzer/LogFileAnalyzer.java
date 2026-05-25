package LogFileAnalyzer;

import java.io.*;
import java.util.*;
import java.time.*;

public class LogFileAnalyzer{

   public static void main(String[] args) {

        Map<String, Integer> levelCounts = new LinkedHashMap<>();
        levelCounts.put("INFO", 0);
        levelCounts.put("WARN", 0);
        levelCounts.put("ERROR", 0);

        List<String> errorMessages = new ArrayList<>();

        LocalDateTime earliest = null;
        LocalDateTime latest = null;

        int totalEntries = 0;

        try (BufferedReader br = new BufferedReader(new FileReader("src/LogFileAnalyzer/Server.log"))) {

            String line;
            int lineNumber = 0;

            while ((line = br.readLine()) != null) {
                lineNumber++;

                try {
                    LogEntry entry = LogEntryParser.parse(line);

                    totalEntries++;

                    String level = entry.getLevel();
                    levelCounts.put(level, levelCounts.get(level) + 1);

                    if (level.equals("ERROR")) {
                        errorMessages.add(entry.getMessage());
                    }

                    if (earliest == null || entry.getTimestamp().isBefore(earliest)) {
                        earliest = entry.getTimestamp();
                    }

                    if (latest == null || entry.getTimestamp().isAfter(latest)) {
                        latest = entry.getTimestamp();
                    }

                } catch (MalformedLogEntryException e) {
                    System.out.println("Skipped line " + lineNumber + ": " + e.getMessage());
                }
            }

            SummaryWriter.write(
                    totalEntries,
                    levelCounts,
                    errorMessages,
                    earliest,
                    latest
            );

            System.out.println("Log analysis complete. Check summary.txt.");

        } catch (FileNotFoundException e) {
            System.out.println("Error: server.log was not found.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}