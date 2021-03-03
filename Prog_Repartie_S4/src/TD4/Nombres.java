package TD4;

public class Nombres {
    private int n;
    private int carre;
    private int pret;

    public Nombres() {
        this.n = 0;
        this.carre = 0;
    }

    public synchronized void calcul(){
        while ( pret == 1 ) { // affichage doit suivre
            try {
                wait(); // bloque jusqu'a un notify()
            } catch ( InterruptedException e ) {}
        }

        n++;
        try {
            Thread.sleep(150); // milliseconds
        }catch (InterruptedException e){}
        carre = n*n;
    }

    public void affiche(){
        while ( pret == 1 ) { // affichage doit suivre
            try {
                wait(); // bloque jusqu'a un notify()
            } catch ( InterruptedException e ) {}
        }

        System.out.println( " valeur : " + n + " et son carre : " + carre );
    }
}
