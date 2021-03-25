package TD6.ex2;

import java.io.*;
import java.net.*;

public class Connexion implements Runnable {

    static int num = 0;
    int ident;
    Socket sockcli = null; // socket pour le client
    DataInputStream in;
    DataOutputStream out;
    boolean boucle = true;

    public Connexion( Socket sockcli ) throws IOException {
        this.sockcli = sockcli;

        //flux de donnees en entre
        in = new DataInputStream( sockcli.getInputStream() );

        //flux de donnes en sortie
        out = new DataOutputStream( sockcli.getOutputStream() );

        ident = Connexion.num++;
    }

    @Override
    public void run() {
        String string;
        BufferedReader ins = new BufferedReader( new InputStreamReader( in ) );
        PrintWriter outs = new PrintWriter( new BufferedWriter( new OutputStreamWriter( out )));

        while ( boucle ) {
            try {
                string = ins.readLine();
                if ( string.equals( "stop" ) ) boucle = false;
                string = "Reponse pour " + string;

                // renvoi d'un echo
                outs.println( string );
            } catch ( Exception e ) {}

            try {
                System.out.println( "Arret de la connexion" );
                ins.close();
                outs.close();
                sockcli.close();
            } catch ( Exception e ) {}
        }

    }
}
