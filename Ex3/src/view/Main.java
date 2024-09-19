package view;
import controller.ThreadBanco;
import java.util.concurrent.Semaphore;


public class Main {

	public static void main(String[] args) {
		Semaphore sem1 = new Semaphore(1);
		Semaphore sem2 = new Semaphore(2);
		for(int i = 1; i <= 20; i++) {
			ThreadBanco th = new ThreadBanco(i, sem1, sem2);
			th.start();
		}
	
	}

}
