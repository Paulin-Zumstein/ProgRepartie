package TD6.ex1;

import java.io.*;
import java.net.*;
import java.util.Scanner;

// Le serveur est donne dans l’appel: java Client pc1
public class Client {

    public static void main(String[] args) throws Exception {

        // le socket vers le serveur
        Socket socket = new Socket(args[0], 6020);

        // Les  messages sont saisis du clavier :Scanner
        // Un BufferedReader permet de lire par ligne
        Scanner ins = new Scanner( new InputStreamReader (socket.getInputStream()) );
        Scanner cons = new Scanner(System.in);

        // Un PrintWriter pou les opérations print
        PrintWriter outs = new PrintWriter( new BufferedWriter ( new OutputStreamWriter (socket.getOutputStream()) ), true);
        String rep;
        String req;

        while(true) {
            req = cons.nextLine(); //console
            outs.println(req);      // envoi du message
            rep = ins.nextLine();   // lecture de a reponse
            if (req.equals("stop"))
                break;
        }

        ins.close();
        outs.close();
        socket.close();
    }
}