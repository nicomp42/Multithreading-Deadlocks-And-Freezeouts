/*
 * Bill Nicholson
 * nicholdw@ucmail.uc.edu
 */
package main;

import java.util.ArrayList;
import java.util.List;

import myThread.MyDeadlockingThread;
import sharedObject.SharedObject;
import uncooperativeThread.UncooperativeThread;

public class Main {

	public static void main(String[] args) {
		competingThreads();
		//uncooperativeThreads();
		
	}
	private static void competingThreads() {
		Object lock1, lock2;
		lock1 = new Object();
		lock2 = new Object();
		List<MyDeadlockingThread> myThreads = new ArrayList<MyDeadlockingThread>();
		int threads = 2;
		for (int i = 0; i < threads; i++) {
			myThreads.add(new MyDeadlockingThread(i, lock1, lock2));
		}

		for (MyDeadlockingThread t : myThreads) {
			t.start();
		}
		//synchronized(lock1) {			// This is playing dirty
			for (MyDeadlockingThread t : myThreads) {
				try {t.join();} catch (Exception ex) {}
			}
		//}
	}
	/**
	 * We will try to call SharedObject.methodC from two threads. 
	 * The first one will get in, the second will have to wait. A freezeout. I made that up.
	 */
	private static void uncooperativeThreads() {
		SharedObject sharedObject = new SharedObject();
		UncooperativeThread t1 = new UncooperativeThread(1, sharedObject);
		UncooperativeThread t2 = new UncooperativeThread(2, sharedObject);
		
		t1.start();
		t2.start();
		
		try {t1.join();} catch (Exception ex) {}
		try {t2.join();} catch (Exception ex) {}
	}
}
