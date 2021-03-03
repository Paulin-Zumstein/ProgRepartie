package TD4;

public class Test {

    public static void main(String[] args) {
        Nombres nombre = new Nombres();

        Thread T1 = new Calcul( nombre );
        Thread T2 = new Affiche( nombre );
        T1.start();
        T2.start();
        try {
            T1.join();
            T2.join();
        } catch ( InterruptedException e) {}
        System.out.println( " fini " );
    }

}

