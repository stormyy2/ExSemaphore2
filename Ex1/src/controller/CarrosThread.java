package controller;

import java.util.concurrent.Semaphore;

public class CarrosThread extends Thread {
	private int idThread;
	private Semaphore semaforo;

	public CarrosThread(int idThread, Semaphore semaforo) {
		this.idThread = idThread;
		this.semaforo = semaforo;
	}

	public void run() {
		atravessaCarro(idThread, semaforo);
	}

	public void atravessaCarro(int id, Semaphore semaforo) {
		try {
			semaforo.acquire();
			switch (id) {
			case 0:
				sleep(1000);
				System.out.println("Carro da esquerda passando! (" + id + ")");
				break;
			case 1:
				sleep(1000);
				System.out.println("Carro de cima passando! (" + id + ")");
				break;
			case 2:
				sleep(1000);
				System.out.println("Carro da direita passando! (" + id + ")");
				break;
			case 3:
				sleep(1000);
				System.out.println("Carro de baixo passando! (" + id + ")");
				break;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		finally {
			semaforo.release();
		}
	}
}
