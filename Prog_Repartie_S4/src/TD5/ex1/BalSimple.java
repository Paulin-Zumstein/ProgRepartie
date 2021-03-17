package TD5.ex1;

import java.util.concurrent.Semaphore;

class BalSimple {
    String message;
    Semaphore sDepot, sRetrait;

    public BalSimple() {
        sDepot =  new Semaphore(1, true);
        sRetrait = new Semaphore(0, true);
    }

    public void deposeRequete(String mess) {

        // j'attend que la boite aux lettres soit libre puis je la rend indisponible
        try { sDepot.acquire(); } catch (InterruptedException e) {e.printStackTrace();}

        // je fais mon action
        message = mess;

        // puis j'indique avoir fini
        sRetrait.release();
    }

    public String retireRequete(){

        //try catch normalement non nécessaire, j'attend que la boite aux lettres contienne un message
        try { sRetrait.acquire(); } catch (InterruptedException e) { e.printStackTrace(); }

        //je fais mon action
        String mess = message;

        //puis j'indique avoir fini
        sDepot.release();

        return mess;
    }
}