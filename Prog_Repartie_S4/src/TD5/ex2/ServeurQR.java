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
        int i = 1;
        while (true){

            // petit dodo
            try { Thread.sleep(random.nextInt(200)); } catch (InterruptedException e) { e.printStackTrace(); }

            // on récupère la question
            try{
                String mess = bal.retirerQ();
                System.out.println("Je suis le serveur au tour de boucle " + i + " et je lis ca        |        " +  mess );

                // petit dodo
                try { Thread.sleep(random.nextInt(200)); } catch (InterruptedException e) { e.printStackTrace(); }

                // on dépose notre réponse dans la boite aux lettres
                bal.deposerR(i + "ème reponse");

            } catch (Exception e) {e.printStackTrace();}

            i++;
        }
        //System.out.println("Serveur fini");
    }
}
