package controller;

import java.util.Random;
import java.util.concurrent.Semaphore;

public class ThreadBanco extends Thread {
	private Semaphore semaforo1;
	private Semaphore semaforo2;
	private int idThread;

	public ThreadBanco(int idThread, Semaphore semaforo1, Semaphore semaforo2) {
		this.idThread = idThread;
		this.semaforo1 = semaforo1;
		this.semaforo2 = semaforo2;
	}

	public void run() {
		Random random = new Random();
		int SaqueDeposito = random.nextInt(2) + 1;
		int valorSaldo = (int) ((Math.random() * 500) + 500);

		try {
			semaforo2.acquire();
			if (SaqueDeposito == 1) {
				semaforo1.acquire();
				executaSaque(idThread, valorSaldo);
				sleep(100);
				semaforo1.release();
			} else {
				semaforo1.acquire();
				executaDeposito(idThread, valorSaldo);
				sleep(100);
				semaforo1.release();
			}
			sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo2.release();
		}
		
	}

	private void executaSaque(int id, int saldo) {
		int diferencaMinMax = saldo - 1;
		int valorSaque = (int) ((Math.random() * diferencaMinMax) + 1);
		System.out.println("Um valor de R$" + valorSaque + " será sacado do banco! Código da conta: " + id);
	}

	private void executaDeposito(int id, int saldo) {
		int valorDeposito = (int) ((Math.random() * 500) + 500);
		System.out.println("Um valor de R$ " + valorDeposito + " foi depositado na conta! Saldo atual: " + (saldo + valorDeposito) + " | | Código da conta: " + id);

	}
}
