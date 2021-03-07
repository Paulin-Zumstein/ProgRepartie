package TD4;

public class Calcul extends Thread{
    private Nombres nombre;

    public Calcul(Nombres nom) {
        this.nombre = nom;
    }

    public void run(){
        for (int i = 1; i< 11; i++){
            System.out.println( "calcul " + i );
            nombre.calcul();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }
    }

    public static void main (String args[]){}
}
