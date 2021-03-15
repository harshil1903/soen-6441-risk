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

    LogEntryBuffer() {
        String l_path = "src/main/resources/";
        String l_fileName = "LogEntry.log";
        FileWriter l_logWriter;
        try {
            this.d_logWriter = new FileWriter(l_path + l_fileName, false);
            System.out.println("New File Created");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void notify(String p_data) {
        try {
            System.out.println("Data will be appended");
            String l_data = "Log: " + p_data + "\n";
            System.out.println(l_data);
            d_logWriter.write(l_data);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
