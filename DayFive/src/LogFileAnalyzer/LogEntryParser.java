package LogFileAnalyzer;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class LogEntryParser {

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static LogEntry parse(String line) throws MalformedLogEntryException {

        if (line == null || !line.startsWith("[") || !line.contains("]")) {
            throw new MalformedLogEntryException("Invalid timestamp brackets.");
        }

        int closeBracket = line.indexOf("]");

        String timestampText = line.substring(1, closeBracket).trim();
        String details = line.substring(closeBracket + 1).trim();

        if (!details.contains(":")) {
            throw new MalformedLogEntryException("Missing colon.");
        }

        String[] parts = details.split(":", 2);
        String level = parts[0].trim();
        String message = parts[1].trim();

        if (!level.equals("INFO") &&
            !level.equals("WARN") &&
            !level.equals("ERROR")) {
            throw new MalformedLogEntryException("Invalid log level.");
        }

        try {
            LocalDateTime timestamp =
                    LocalDateTime.parse(timestampText, FORMATTER);

            return new LogEntry(timestamp, level, message);

        } catch (DateTimeParseException e) {
            throw new MalformedLogEntryException("Invalid timestamp.");
        }
    }

    public static DateTimeFormatter getFormatter() {
        return FORMATTER;
    }
}