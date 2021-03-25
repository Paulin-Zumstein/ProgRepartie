package TD6.ex2;

import java.io.*;
import java.net.*;

public class Connexion implements Runnable {

    static int num = 0;
    int ident;

    // socket pour le client
    Socket sockcli = null;

    // objet pour les read
    DataInputStream in;

    // objet pour les write
    DataOutputStream out;

    boolean boucle = true;

    public Connexion( Socket sockcli ) throws IOException {

        // socket deja connecté
        this.sockcli = sockcli;

        //flux de donnees en entre connecté au stream d'entrée de la socket  -->   pour les read
        in = new DataInputStream( sockcli.getInputStream() );

        //flux de donnees en sortie connecté au stream de sortie de la socket   -->   pour les write
        out = new DataOutputStream( sockcli.getOutputStream() );

        ident = Connexion.num++;
    }

    @Override
    public void run() {
        String string;

        // Initialisation de l'objet haut niveau de lecture de l'entrée de la socket
        BufferedReader ins = new BufferedReader( new InputStreamReader( in ) );

        // Initialisation de l'objet haut niveau d'écriture de la sortie de la socket
        PrintWriter outs = new PrintWriter( new BufferedWriter( new OutputStreamWriter( out )));

        while ( boucle ) {

            try {

                // On fait un read dans le stream d'entre de la socket
                string = ins.readLine();

                // Si le message lu est stop l'exécution de la boucle
                if ( string.equals( "stop" ) ) boucle = false;

                string = "Reponse pour " + string;

                // On écrit dans le stream de la socket pour envoyer la réponse
                outs.println( string );

            } catch ( Exception e ) {}

        }

        try {
            System.out.println( "Arret de la connexion" );

            // Fermeture des outils de communication
            ins.close();
            outs.close();
            sockcli.close();

        } catch ( Exception e ) {}

    }
}
