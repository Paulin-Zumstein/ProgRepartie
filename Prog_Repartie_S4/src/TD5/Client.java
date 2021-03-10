package TD5;

public class Client implements Runnable {
    private BalSimple bal;

    public Client (BalSimple bal){
        this.bal = bal;
    }

    @Override
    public void run() {
        for (int i = 1; i<11; i++){
            String message = "bien ou quoi ? Moi c'est le client num : " + i;
            bal.deposeRequete(message);
        }
    }
}
