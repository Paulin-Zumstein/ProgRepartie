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
        client = new Connexion[10];
        try {	// lancer le socket pour ecouter le port
            sockserv = new ServerSocket(6020);   // Port
            this.es = es;  // un gestionnaire de taches
        }	catch ( IOException ex ) {}
    }

    public void run() {
        System.out.println( "SERVER START " );
        String str ="";
        try {
            while(true) {
                Socket sockcli = sockserv.accept(); //attente
                if (nbcl < 9) {
                    client[nbcl] =  new Connexion (sockcli);
                    // lancer la tache pour gerer le client
                    es.execute(client[nbcl]);
                    nbcl++;
                }
                else  System.out.println("pb : trop de clients");
            }  //while
        }   // try
        catch ( Exception ex ) {}

        finally{
            try {sockserv.close(); // fermeture port 6020
                es.shutdown();
            } catch ( IOException ex ) {}
        }
    }   /// run

    public static void main(String[] args) throws Exception {
        System.out.println( "START" );
        ExecutorService es = Executors.newFixedThreadPool(10);
        Serveur2 serv = new Serveur2( es );
        es.execute( serv );	// execution de la tache principale
    }
}