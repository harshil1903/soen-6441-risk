package com.risk.exception;

/**
 * This class defines user defined exception.
 *
 * @author Harshil
 */
public class InvalidMapException extends Exception {

    /**
     * This method throws user define exception if map is not valid.
     *
     * @param p_Message Message for the exception
     */
    public InvalidMapException(String p_Message) {
        super(p_Message);

    }

}