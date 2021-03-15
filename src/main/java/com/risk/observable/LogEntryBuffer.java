package com.risk.observable;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Observable class
 *
 * @author Chirag
 */
public class LogEntryBuffer {
    FileWriter d_logWriter;
    StringBuilder d_stringBuilder;

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

    public void notify(String p_data) {
        d_stringBuilder.append("Log: " + p_data + "\n");
        try {
            String l_fileName = "src/main/resources/LogEntry.log";
            d_logWriter = new FileWriter(l_fileName, true);
            String l_data = "Log: " + p_data + "\n";
            System.out.println(l_data);
            d_logWriter.write(l_data);
            d_logWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
