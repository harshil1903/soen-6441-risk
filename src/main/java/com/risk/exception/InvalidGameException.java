package com.risk.exception;

/**
 * This class defines user defined exception.
 *
 * @author Chirag
 */
public class InvalidGameException extends Exception {

    /**
     * This method throws user define exception if game is not valid.
     *
     * @param p_message Message for the exception
     */
    public InvalidGameException(String p_message) {
        super(p_message);

    }
}