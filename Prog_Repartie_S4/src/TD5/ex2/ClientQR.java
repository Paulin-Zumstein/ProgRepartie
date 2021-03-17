package TD5.ex2;

import java.util.Random;

public class ClientQR implements Runnable {
    private BalQR bal;
    private Random random = new Random();
    private int nb;


    public ClientQR(BalQR bal, int nb){
        this.bal = bal;
        this.nb = nb;
    }

    @Override
    public void run() {
        for (int i = 1; i<11; i++){
           try{

               // petit dodo
               Thread.sleep(random.nextInt(200));

               // on pose notre question
               bal.deposeQ("client " + nb + " | " + i +  ", voila mon message ");

               // on récupère la réponse
               System.out.println("Reponse du client " + nb + " | " + i + "        |         " + bal.retirerR());


           } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("Client " + nb + " fini");
    }
}
