package TD4;

public class Affiche extends Thread{
    private Nombres nombre;

    public Affiche(Nombres nom){
        this.nombre = nom;
    }

    public void run(){

        //On dort un peu pour s'assurer que le threads calcul commence a travailler sur nombre avant le thread affiche
        try {
            Thread.sleep(150); // milliseconds
        }catch (InterruptedException e){}

        for (int i = 1; i < 11; i++) {
            System.out.println( " Affiche " + i );
            nombre.affiche();
            try {
                Thread.sleep(150);
            } catch ( InterruptedException e ) {}
        }
    }

    public static void main(String[] args) {
    }
}
