package myThread;

import java.util.Random;

public class MyDeadlockingThread extends Thread {
	private int id;
	Object lock1, lock2;
	
	public MyDeadlockingThread(int id, Object lock1, Object lock2) {
		this.id = id;
		this.lock1 = lock1;
		this.lock2 = lock2;
	}
	public void run() {
		doStuff();
	}
	private void doStuff() {
		int counter = 0;
		
		// When run in multiple threads, this will eventually deadlock... 
		// In one thread:
		//   The first set of nested locks will grab lock1 and wait for lock2
		// In the other thread:
		//   The second set of nested locks will grab lock2 and wait for lock1
		while (true) {
			counter++;
			/*if (counter % 1000000 == 0) */ {System.out.println("Thread " + id + ", " + counter);}
			synchronized(lock1) {
				synchronized(lock2) {
					System.out.println("Thread " + id + " lock1");
					try {Thread.sleep((new Random()).nextInt(1000));} catch(Exception ex) {}
				}
			}
			synchronized(lock2) {
				synchronized(lock1) {
					System.out.println("Thread " + id + " lock2");
					try {Thread.sleep((new Random()).nextInt(1000));} catch(Exception ex) {}				
				}
			}
		}
	} 
}
