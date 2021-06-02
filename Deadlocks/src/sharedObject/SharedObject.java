package sharedObject;

import java.util.Random;

public class SharedObject {
	/***
	 * A method that is single-threaded and will stay busy for a pretty long time so the second thread will have to wait.
	 * @param id
	 */
	public synchronized void methodA(int id) {
		int sleepTime = 100000;
		System.out.println("methodC(): Thread " + id + ", sleeping for " + sleepTime / 1000 + " seconds.");
		// Stay busy for a while so no other threads can get in here
		try {Thread.sleep((new Random()).nextInt(sleepTime));} catch(Exception ex) {}
	}
}
