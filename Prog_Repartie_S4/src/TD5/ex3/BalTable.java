package TD5.ex3;

import java.util.concurrent.Semaphore;

class BalTable {
    int indiceDepot, indiceRetrait, nb;
    String message [];
    Semaphore sDepot, sRetrait;

    public BalTable (int nbcases) {
        this.nb = nbcases;
        this.message = new String[this.nb]; // tableau de nb String

        // initialisation des indices
        this.indiceDepot = 0;
        this.indiceRetrait = 0;

        // represente le nombre de cases vides ------------- ce semaphore accept nb appels non bloquants
        sDepot = new Semaphore(nb, true);

        // represente le nombre de requete fait en attente de traitement ------------- ce semaphore accept 0 appels non bloquants
        sRetrait = new Semaphore(0, true);
    }

    public void deposeRequete(String mess) throws InterruptedException {

        // on regarde si on peut y aller sinon on attend la permission
        sDepot.acquire();

        // on dépose le message dans la case adequat
        System.out.println("depose : " + mess + " indice : " + indiceDepot);
        message[indiceDepot] = mess;

        // on met à jour l'indice de dépot
        indiceDepot++;
        if (indiceDepot == nb) indiceDepot = 0;

        // on incrémente le jeton de sRetrait
        sRetrait.release();
    }

    public String retireRequete() throws InterruptedException {

        // on regarde si quelqu'un a deja fait un release()
        sRetrait.acquire();

        // on travaillle
        String mess = this.message[indiceRetrait];
        indiceRetrait++;
        if (indiceRetrait == nb) indiceRetrait = 0;

        // on
        sDepot.release();
        return mess;
    }
}