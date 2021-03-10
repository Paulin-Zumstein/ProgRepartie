package TD5;

public class Serveur implements Runnable{
    private BalSimple bal;

    public Serveur (BalSimple bal){
        this.bal = bal;
    }

    @Override
    public void run() {
        for (int i = 1; i<11; i++){
            System.out.println("Je suis le serveur et je lit ca" + bal.retireRequete());
        }
    }
}
