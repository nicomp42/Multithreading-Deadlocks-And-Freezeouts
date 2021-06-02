package uncooperativeThread;

import sharedObject.SharedObject;

public class UncooperativeThread extends Thread {
	private int id;
	SharedObject sharedObject;

	public UncooperativeThread(int id, SharedObject sharedObject) {
		this.id = id;
		this.sharedObject = sharedObject;
	}
	public void run() {
		sharedObject.methodA(id);
	}
	
}
