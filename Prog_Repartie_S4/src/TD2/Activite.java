package TD2;

import java.util.Random;

public class Activite {
    private int id;    // un identifiant
    //private int delai;
    private Random random;
    private int duree;
    private static char[] S1 = {' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' ',' '};
    private  String B; // pour affichage

    public Activite (int nb, /*int pause,*/ int lng) {
        id=nb;
        //delai = pause;
        random = new Random();
        duree = lng;
        B = new String(S1,0,id); // la longueur de B est proportionnelle a id
    }

    public void faire() {
        double f = 1.3333;  // une valeur arbitraire
        for (int nombre=1; nombre <duree; nombre++) {
            System.out.println("T" + id + " \t|" + B + 'x'); // on laisse une trace
            for (int i=1; i <10000000; i++) f *= 1.000001;  // des multiplications pour consommer le temps
            try {
                Thread.sleep(random.nextInt(100)); // milliseconds
            } catch (InterruptedException e) {  }
        }
        System.out.println("Fin activite T" + id);
    }
}
