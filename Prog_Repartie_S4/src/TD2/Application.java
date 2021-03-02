package TD2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Application {
    public static void main(String [] args) {
        ExecutorService es;
        TR t;
        System.out.println(" debut tache principale ");
        es = Executors.newFixedThreadPool(3);
        t = new TR(1,100,10);
        es.execute(t);
        t = new TR(2,150,10);
        es.execute(t);
        t = new TR(3,100,10);
        es.execute(t);
        es.shutdown();
        System.out.println(" fin tache principale ");
    }
}