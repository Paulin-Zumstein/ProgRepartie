package TD4;

public class Nombres {
    private int n;
    private int carre;

    // indicateur permettant aux threads de communiquer ensemble
    private int pret;

    public Nombres() {
        this.n = 0;
        this.carre = 0;

        //on initilise pour que le premiers threads ne rentre pas dans le if du wait()
        this.pret = 0;
    }

    public synchronized void calcul(){

        //si la ressource est non disponible on wait sinon on la rend non dispo et on travail
        while ( pret == 1 ) {
            try {
                wait(); // bloque jusqu'a un notify()
            } catch ( InterruptedException e ) {}
        }
        reset();
        n++;
        try {
            Thread.sleep(150); // milliseconds
        }catch (InterruptedException e){}
        carre = n*n;
        set();

    }

    public synchronized void affiche(){

        //si la ressource est non disponible on wait sinon on la rend non dispo et on travail
        while ( pret == 1 ) {
            try {
                wait(); // bloque jusqu'a un notify()
            } catch ( InterruptedException e ) {}
        }

        reset();

        System.out.println( " valeur : " + n + " et son carre : " + carre );

        set();

    }

    public synchronized void set(){
        pret = 0;
        notifyAll();
    }

    public synchronized  void reset(){
        pret = 1;
    }
}
