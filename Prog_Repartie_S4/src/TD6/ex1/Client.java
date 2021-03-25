package TD6.ex1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Le serveur est donne dans l’appel: java Client pc1
public class Client {

    public static void main(String[] args) throws Exception {

        // Création de la socket de connection au serveur avec ses coordonnées données en paramètre
        Socket socket = new Socket(args[0], 6020);

        // On affecte un objet scanner au flux d'entré de la socket (lecture)
        Scanner ins = new Scanner( new InputStreamReader (socket.getInputStream()) );

        // On affecte un objet scanner au flux d'entré clavier
        Scanner cons = new Scanner(System.in);

        // On créé un objet PrintWriter relié au stream de sortie de la socket  -->  ecrivain
        PrintWriter outs = new PrintWriter( new BufferedWriter ( new OutputStreamWriter (socket.getOutputStream()) ), true);
        String rep;
        String req;

        while(true) {

            // On récupère l'entrée clavier  //console
            req = cons.nextLine();

            // On write dans la socket pour l'envoyer  // envoi du message
            outs.println(req);

            // On regarde la réponse  // lecture de a reponse
            rep = ins.nextLine();

            if (req.equals("stop"))
                break;
        }

        ins.close();
        outs.close();
        socket.close();
    }
}