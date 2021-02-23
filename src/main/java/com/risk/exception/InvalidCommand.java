package com.risk.exception;

/**
 * This class defines user defined exception for Invalid Command.
 *
 * @author Harshil
 */
public class InvalidCommand extends Exception {

    /**
     * This method throws user define exception if command is not valid.
     *
     * @param p_Message Message for the exception
     */
    public InvalidCommand(String p_Message) {
        super(p_Message);

    }

}
