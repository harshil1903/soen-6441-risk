package com.risk.gamelogs;

/**
 * Observable class
 *
 * @author Chirag
 */
public class LogEntryBuffer extends Observable {
    /**
     * StringBuilder to keep appended data.
     */
    public static StringBuilder d_stringBuilder;

    /**
     * Default constructor for LogEntryBuffer class.
     * Initializes class Members.
     */
    public LogEntryBuffer() {

        d_stringBuilder = new StringBuilder();
    }

    /**
     * Writes data to log file and appends to StringBuilder.
     *
     * @param p_data log data
     */
    public void notify(String p_data) {
        d_stringBuilder.append("Log: " + p_data + "\n");
        notifyObservers(this);
    }
}
