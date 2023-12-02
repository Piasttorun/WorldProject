package com.sparta.jarjarbinks.worldproject.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class WorldServiceLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " " +
                LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + "  " +
                "Action: " + record.getSourceMethodName() + "  " +
                "Message: " + record.getMessage() + "\n";
    }
}
