package TD5.ex1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Ex1Test {
    public static void main(String[] args) {

        // on fait un gestionnaire de threads
        ExecutorService es = Executors.newFixedThreadPool(4);

        BalSimple boite = new BalSimple();

        // on créer nos runnables
        Client client1 = new Client(boite, 1);
        Client client2 = new Client(boite, 2);
        Client client3 = new Client(boite, 3);

        Serveur serveur = new Serveur(boite);

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
