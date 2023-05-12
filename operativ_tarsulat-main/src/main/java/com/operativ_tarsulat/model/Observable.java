package com.operativ_tarsulat.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import com.operativ_tarsulat.view.Observer;

/*
 * Common parent class for all observable classes. Used for notifying observers.
 */
public class Observable implements Serializable {
	/*
	 * List for storing registered observers
	 */
	private List<Observer> observers;
	/*
	 * Constructor, initializes the observers variable
	 */
	public Observable() {
		observers = new LinkedList<Observer>();
	}
	/*
	 * Registers a new observer
	 */
	public void AddObserver(Observer observer) {
		observers.add(observer);
	}
	/*
	 * Removes all observers from the observed list, previously registered observers will no longer be updated
	 */
	public void RemoveObservers() {
		observers.clear();
	}
	/*
	 * Notifies all observers, that a change might have happened to the object 
	 */
	public void NotifyAll() {
		for(Observer o : observers)
			o.Update();
	}
	
}
