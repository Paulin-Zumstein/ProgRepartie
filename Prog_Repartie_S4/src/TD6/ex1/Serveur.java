package TD6.ex1;
import java.io.*;
import java.net.*;

public class Serveur {

    public static void main(String[] args) throws Exception {

        // On créé une ServerSocket pour écouter le port 6020
        ServerSocket s = new ServerSocket(6020);

        System.out.println("START");

        // On attend la demande d'un client sur le port 6020. Quand un client est là, la fonction accept() crée un objet Socket connecté au client
        Socket soc = s.accept();

        // Un BufferedReader permet de lire par ligne  -->  objet permettant read   |   soc.getInputStream = descripteur de la socket de lecture, d'entrée
        BufferedReader ins = new BufferedReader( new InputStreamReader(soc.getInputStream()) );

        // Un PrintWriter possede toutes les operations print classiques
        // En mode auto-flush, le tampon est vide (flush) a l'appel de println  -->  objet permettant write   |   soc.getOutputStream = descripteur de la socket d'écriture, de sortie
        PrintWriter outs = new PrintWriter( new BufferedWriter( new OutputStreamWriter(soc.getOutputStream()) ), true );

        while (true) {

            // Lecture du message
            String str = ins.readLine();

            // Si stop alors on stop l'execution de la boucle
            if (str.equals("stop")) break;

            String rep = "Recu : " + str;
            System.out.println(rep);

            // Écriture d'un echo
            outs.println(rep);
        }

        // On ferme les objects
        ins.close();
        outs.close();
        soc.close();
    }
}