package TD4;// Fichier:_partage.java

import java.util.concurrent.Semaphore;

public class partage extends Thread {

    private static String chaineCommune = "";
    private static int cpt = 0;
    private String nom;
    private Semaphore semaphore;

    partage ( String s, Semaphore semaphore) {
        nom = s;
        this.semaphore= semaphore;
    }

    public void run() {
       for (int i = 0; i<10; i++)
        {
            //je bloque les autres threads
            try {
                semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            maj(nom); // mises a jour

            //je libaire les autres threads
            semaphore.release();

            // puis je dors
            try { sleep(100); } catch(InterruptedException e) {}
        }
    }

   public  void maj(String nn){
        chaineCommune = chaineCommune + nn;
        cpt++;
   }

    public static void main(String args[]) {

        Semaphore semaphore = new Semaphore(1, true);
        Thread T1 = new partage( "T1 ", semaphore );
        Thread T2 = new partage( "T2 ", semaphore );
        Thread T3 = new partage( "T3 ", semaphore );

        T1.start();
        T2.start();
        T3.start();
        try {
            T1.join();
            T2.join();
            T3.join();
        } catch(InterruptedException e) {}
        System.out.println( chaineCommune );
        System.out.println( cpt );
    }
}

 
