package TD5.ex3;

import java.util.concurrent.Semaphore;

class BalTable {
    int indiceDepot, indiceRetrait, nb;
    String message [];
    Semaphore sDepot, sRetrait;

    public BalTable (int nbcases) {
        this.nb = nbcases;
        this.message = new String[this.nb]; // tableau de nb cha\^{\i}nes
        this.indiceDepot = 0; // initialisation des indices
        this.indiceRetrait = 0;
    }

    public void deposeRequete(String mess) {
    }

    public String retireRequete() {
        return "";
    }
}