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
            String message = "bien ou quoi ? Moi c'est le client num : " + nb +" | "+ i;
            try {
                bal.deposeRequete(message);
            } catch (Exception e) {e.printStackTrace();}
            try {
                Thread.sleep(random.nextInt(200));
            } catch (InterruptedException e) { e.printStackTrace(); }
        }
        System.out.println("Client " + nb + " fini");
    }
}
