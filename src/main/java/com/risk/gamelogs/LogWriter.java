package com.risk.gamelogs;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter implements Observer {
    String l_fileName = "src/main/resources/LogEntry.log";
    FileWriter d_logWriter;

    {
        try {
            d_logWriter = new FileWriter(l_fileName, false);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }


    @Override
    public void update(Observable p_observable_state) {
        try {
            String l_fileName = "src/main/resources/LogEntry.log";
            d_logWriter.write("What to be added");
            d_logWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
