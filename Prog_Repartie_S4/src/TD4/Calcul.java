package TD4;

public class Calcul extends Thread{
    private Nombres nom;

    public Calcul(Nombres nom) {
        this.nom = nom;
    }

    public void run(){
        for (int i = 0; i< 10; i++){
            System.out.println( "calcul " + i );
            nom.calcul();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    public static void main (String args[]){}
}
