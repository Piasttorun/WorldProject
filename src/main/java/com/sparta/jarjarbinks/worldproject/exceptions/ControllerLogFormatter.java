package com.sparta.jarjarbinks.worldproject.exceptions;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class ControllerLogFormatter extends Formatter {
    @Override
    public String format(LogRecord record) {
        return LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE) + " " +
                LocalDateTime.now().format(DateTimeFormatter.ISO_TIME) + "  " +
                "IP: " + record.getMessage() + "  " +
                "Action: " + record.getSourceMethodName() + "\n";
    }
}
