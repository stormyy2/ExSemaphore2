package view;
import controller.ThreadCorredor;
import java.util.concurrent.Semaphore;


public class Main {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);
		for(int i = 1; i <= 4; i++) {
			ThreadCorredor th = new ThreadCorredor(i, semaforo);
			th.start();
		}
	}

}
