package com.risk.observable;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Observable;

/**
 * Observable class
 *
 * @author Chirag
 */
public class LogEntryBuffer extends Observable {


    /**
     * FileWriter to write log file.
     */
    FileWriter d_logWriter;

    /**
     * StringBuilder to keep appended data.
     */
    StringBuilder d_stringBuilder;

    /**
     * Default constructor for LogEntryBuffer class.
     * Initializes class Members.
     */
    public LogEntryBuffer() {
        d_stringBuilder = new StringBuilder();
        String l_fileName = "src/main/resources/LogEntry.log";
        FileWriter l_logWriter;
        try {
            this.d_logWriter = new FileWriter(l_fileName, false);
            this.d_logWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Writes data to log file and appends to StringBuilder.
     *
     * @param p_data
     */
    public void notify(String p_data) {
        d_stringBuilder.append("Log: " + p_data + "\n");
        try {
            String l_fileName = "src/main/resources/LogEntry.log";
            d_logWriter = new FileWriter(l_fileName, true);
            String l_data = "Log: " + p_data + "\n";
            //System.out.println(l_data);
            d_logWriter.write(l_data);
            d_logWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
