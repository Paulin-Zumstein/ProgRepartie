package TD5.ex2;

import java.util.Random;

public class ServeurQR implements Runnable{
    private BalQR bal;
    private Random random = new Random();

    public ServeurQR(BalQR bal){
        this.bal = bal;
    }

    @Override
    public void run() {
        int i = 0;
        while (true){
            try {
                Thread.sleep(random.nextInt(200) + 100);
            } catch (InterruptedException e) { e.printStackTrace(); }
            try{
                System.out.println("Je suis le serveur " + i + " et je lis ca" + bal.retireRequete());
            } catch (Exception e) {e.printStackTrace();}
            i++;
        }
        //System.out.println("Serveur fini");
    }
}
