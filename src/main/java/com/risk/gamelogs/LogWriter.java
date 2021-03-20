package com.risk.gamelogs;

import java.io.FileWriter;
import java.io.IOException;

/**
 * implements functionality of observer class
 *
 * @author Chirag
 */

public class LogWriter implements Observer {
    /**
     * Writes data to log file
     */
    FileWriter d_logWriter;


    /**
     * implementation of abstract method of observer
     *
     * @param p_observable_state: Object that is passed by the subject (observable). Very often, this
     */
    @Override
    public void update(Observable p_observable_state) {

        try {
            d_logWriter = new FileWriter("src/main/resources/LogEntry.log", false);
            d_logWriter.write(LogEntryBuffer.d_stringBuilder.toString());
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
