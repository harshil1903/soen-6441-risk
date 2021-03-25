package com.risk.orders;

/**
 * The interface Order.
 *
 * @author Harshil
 */
public interface Order {
    /**
     * Execute.
     */
    public void execute();

    /**
     * Valid boolean.
     *
     * @return the boolean
     */
    public boolean valid();

    /**
     * Print order.
     */
    public void printOrder();

}

