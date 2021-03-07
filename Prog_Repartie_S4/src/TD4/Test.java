package TD4;

public class Test {

    public static void main(String[] args) {
        Nombres nombre = new Nombres();

        //On crée nos deux threads qui possède chacun sa mission
        Thread T1 = new Calcul( nombre );
        Thread T2 = new Affiche( nombre );

        //On lance les deux threads qui vont exécuter leur méthode run()
        T1.start();
        T2.start();

        //On atend la fin des deux threads
        try {
            T1.join();
            T2.join();
        } catch ( InterruptedException e) {}
        System.out.println( " fini " );
    }

}

