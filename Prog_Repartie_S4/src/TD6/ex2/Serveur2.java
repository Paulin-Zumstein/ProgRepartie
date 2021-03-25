package TD6.ex2;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Serveur2  implements Runnable {

    int nbcl;			// nb de clients (max 10)
    Connexion client[];	// les clients
    ExecutorService es;	// le groupe de taches
    ServerSocket sockserv=null;	// socket pour attendre

    public Serveur2( ExecutorService es ) {

        nbcl = 0;
        client = new Connexion[10]; // 10 max

        try {

            // lancement de la socket pour ecouter le port
            sockserv = new ServerSocket(6020);   // Port

            // gestionnaire des taches
            this.es = es;

        }	catch ( IOException ex ) {}
    }

    // try suivi d’une clause finally pour éviter le blocage du port 6020 lors de fermetures intempestives de l’application. Le bloc finally doit fermer la socket d’écoute du serveur.
    public void run() {

        System.out.println( "SERVER START " );
        String str ="";

        try {

            while(true) {

                // Fonction bloquant qui attend la connexion d'un client
                Socket sockcli = sockserv.accept(); //attente

                // Vérification du nmobre de client pour éviter la surcharge
                if (nbcl < 9) {

                    // On mémorise la connexoin
                    client[nbcl] =  new Connexion (sockcli);

                    // lancer la tache pour gerer le client
                    es.execute(client[nbcl]);
                    nbcl++;

                } else  System.out.println("pb : trop de clients");
            }  //while
        } catch ( Exception ex ) {}

        // Si jamais quelqu'un decide stop l'application
        finally{

            // fermeture port 6020 pour ne pas le bloquer
            try {sockserv.close();
                es.shutdown();
            } catch ( IOException ex ) {}

        }
    }   /// run

    public static void main(String[] args) throws Exception {

        System.out.println( "START" );

        // Création du gestionniare de treads
        ExecutorService es = Executors.newFixedThreadPool(10);
        Serveur2 serv = new Serveur2( es );

        // execution de la tache principale
        es.execute( serv );
    }
}