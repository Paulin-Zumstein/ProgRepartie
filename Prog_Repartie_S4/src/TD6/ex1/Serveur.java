package TD6.ex1;
import java.io.*;
import java.net.*;

public class Serveur {

    public static void main(String[] args) throws Exception {

        // lancer le socket pour ecouter le port // ServerSocket est en écoute et renvois une socket quand un client arrive
        ServerSocket s = new ServerSocket(6020);

        System.out.println("START");

        // attente du client
        Socket soc = s.accept();

        // Un BufferedReader permet de lire par ligne  //lecteur
        BufferedReader ins = new BufferedReader( new InputStreamReader(soc.getInputStream()) );

        // Un PrintWriter possede toutes les operations print classiques
        // En mode auto-flush, le tampon est vide (flush) a l'appel de println   // ecrivain
        PrintWriter outs = new PrintWriter( new BufferedWriter( new OutputStreamWriter(soc.getOutputStream()) ), true );

        while (true) {

            // lecture du message
            String str = ins.readLine();
            if (str.equals("stop")) break;
            String rep = "Recu : " + str;
            System.out.println(rep);

            // renvoi d'un echo
            outs.println(rep);
        }

        ins.close();
        outs.close();
        soc.close();
    }
}