package com.risk.gamelogs;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that implements the connection/disconnection mechanism between
 * observers and observables (subject). It also implements the notification
 * mechanism that the observable will trigger when its state changes.
 *
 * @author Rishabh
 */
public class Observable {
    private List<Observer> observers = new ArrayList<Observer>();

    /**
     * Dttach an Observer to the Observable.
     *
     * @param p_observer: view to be added to the list of observers to be notified.
     */
    public void attach(Observer p_observer) {
        this.observers.add(p_observer);
    }

    /**
     * Detach an Observer to the Observable.
     *
     * @param p_observer: view to be removed from the list of observers.
     */
    public void detach(Observer p_observer) {
        if (!observers.isEmpty()) {
            observers.remove(p_observer);
        }
    }

    /**
     * Notify all Observers of the log changes
     *
     * @param p_observable: object that contains the information to be observed.
     */
    public void notifyObservers(Observable p_observable) {
        for (Observer observer : observers) {
            observer.update(p_observable);
        }
    }
}
