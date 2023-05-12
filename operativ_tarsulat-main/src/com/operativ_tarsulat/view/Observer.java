package com.operativ_tarsulat.view;

import java.io.Serializable;

/*
 * Common interface for observer objects. Can be stored in Observable classes and notified if the Observable class has changed
 */
public interface Observer extends Serializable {
	/*
	 * This method is called, when the Observed class has been modified
	 */
	public void Update();
}
