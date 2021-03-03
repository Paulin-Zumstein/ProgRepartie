package TD4;

public class Affiche extends Thread{
    private Nombres nom;

    public Affiche(Nombres nom){
        this.nom = nom;
    }

    public void run(){
        for (int i = 0; i < 10; i++) {
            System.out.println( " Affiche " + i );
            nom.affiche();
            try {
                Thread.sleep(150);
            } catch ( InterruptedException e ) {}
        }
    }

    public static void main(String[] args) {
    }
}
