package TD5.ex2;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex2Test {
    public static void main(String[] args) {
        // on fait un gestionnaire de threads
        ExecutorService es = Executors.newFixedThreadPool(4);

        BalQR boite = new BalQR();

        // on créer nos runnables
        ClientQR client1 = new ClientQR(boite, 1);
        ClientQR client2 = new ClientQR(boite, 2);
        ClientQR client3 = new ClientQR(boite, 3);

        ServeurQR serveur = new ServeurQR(boite);

        System.out.println("debut tache principale");

        // on lance nos runnable entant que thread
        es.execute(serveur);
        es.execute(client1);
        es.execute(client2);
        es.execute(client3);

        // ferme l'entrée càd qu'il n'y a plus d'execute() possible
        es.shutdown();
    }
}
