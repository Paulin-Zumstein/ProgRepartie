package TD4;

import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

class ATache implements Runnable  { // implémentation de l'interface Runnable
	int nom;   // nom de la tâche
	int index; // index de la boucle d'affichage
	Semaphore semPerso;
	Semaphore semVoisin;
	Semaphore semFin;

	public ATache (int ident, Semaphore semPerso, Semaphore semVoisin, Semaphore semFin){
	  this.nom = ident;
	  this.index = 1;

	  //initialisation denos semaphore
	  this.semPerso = semPerso;
	  this.semVoisin = semVoisin;
	  this.semFin = semFin;
	}

	public void run () {
	  System.out.println(" début tâche T"+this.nom);
	  Random rand = new Random();
	  int pause;
	  while (index <= 30){
	    	try{

	    		// on attend que le voisin nous donne la main
				semPerso.acquire();

				// on fait notre action
				System.out.println("indice: " + index + ", tâche T" + this.nom);
				pause = rand.nextInt(200 - 100 + 1) + 100;
				Thread.sleep(pause);
				index++;
			} catch(InterruptedException e) {
	    	System.out.println("Interrupted Exception caught");}

	    	// on redonne la main au voisin
	    	semVoisin.release();
	   }

	  // on donne la main au main qui attend plusieurs release
	  semFin.release();
	  System.out.println("Fin tâche T"+this.nom);
	}

	public static void main(String[] args) {

		//Notre gestionniare de threads
		ExecutorService es = Executors.newFixedThreadPool(3);

		//Les semaphores qui vont nous permettre de faire communiquer les threads ensemble
		Semaphore sem1 = new Semaphore(1, true);
		Semaphore sem2 = new Semaphore(0, true);
		Semaphore sem3 = new Semaphore(0, true);
		Semaphore semFin = new Semaphore(0, true);

		//On crée nos deux threads qui possède chacun sa mission
		es.execute(new ATache( 1, sem1, sem2, semFin));
		es.execute(new ATache( 2, sem2, sem3, semFin));
		es.execute(new ATache( 3, sem3, sem1, semFin));

		try {
			semFin.acquire(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Fin tache principale");

	}
 }
