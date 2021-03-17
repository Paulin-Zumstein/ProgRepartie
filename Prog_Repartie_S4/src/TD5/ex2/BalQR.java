package TD5.ex2;

import java.util.concurrent.Semaphore;

public class BalQR {
    public String message;
    private Semaphore sDepot, sRetrait, sReponse;

    public BalQR(){
        sDepot =  new Semaphore(1, true);
        sRetrait = new Semaphore(0, true);
        sReponse = new Semaphore(0, true);
    }

    // pour deposer la question à l'initiative du client
    public void deposeQ(String mess)throws InterruptedException{
        sDepot.acquire();
        this.message = mess;
        sRetrait.release();
    }

    // recuperer une question à l'initiative du serveur
    public String retirerQ()throws InterruptedException{
        sRetrait.acquire();
        String mess = this.message;
        return mess;
    }

    // deposer une reponse à l'initiative du serveur
    public void deposerR(String mess)throws InterruptedException{
        this.message = mess;
        sReponse.release();
    }

    // recuperer la reponse à l'initiative du client
    public String retirerR()throws InterruptedException{
        sReponse.acquire();
        String mess = this.message;
        sDepot.release();
        return mess;
    }
}
