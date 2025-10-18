package com.haloofwar.threads;

public class RetryThread extends Thread {

	private ClientThread mainThread;
	private long waitingTime = 2000;
	
	public RetryThread(ClientThread mainThread) {
		this.mainThread = mainThread;
	}

	@Override
	public void run() {
		while(!this.mainThread.isConnected() && !this.mainThread.isEnd()) {
			try {
				Thread.sleep(this.waitingTime);
				
				if(!this.mainThread.isConnected() && !this.mainThread.isEnd()) {
					this.mainThread.sendMessage("Connect");
				}
				
			} catch (InterruptedException e) {

			}
		}
	}
}
