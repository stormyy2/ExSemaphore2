package controller;

import java.util.concurrent.Semaphore;

public class ThreadCorredor extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public ThreadCorredor(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	public void run() {
		cruzaCorredor(idThread);
		cruzaPorta(idThread, semaforo);
	}

	private void cruzaCorredor(int id) {
		int totalCorredor = 200;
		int percorrido = 0;
		while (percorrido < totalCorredor) {
			try {
				percorrido += (int) ((Math.random() * 2) + 4);
				sleep(1000);
				if(percorrido >= totalCorredor) {
					System.out.println("A pessoa que está no corredor " + id + " chegou na porta!" );
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("A pessoa que está no corredor " + id + " percorreu: " + percorrido + "m de 200m!");
		}
	}
	
	private void cruzaPorta(int id, Semaphore semaforo) {
		int tempo = (int) ((Math.random() * 1) + 1);
		try {
			semaforo.acquire();
			sleep(tempo);
			System.out.println("A pessoa do corredor " + id + " atravessou a porta!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
		
	}

}
