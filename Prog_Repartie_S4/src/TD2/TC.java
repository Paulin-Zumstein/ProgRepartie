package TD2;

import java.util.Random;
import java.util.concurrent.*;

public class TC implements Callable<Integer> {

    private int nbr;

    public TC(int nbr) {
        this.nbr = nbr;
    }

    //si besoin, la méthode peut lancer une exception/ ici c'est pas le cas car il y a deja un try catch
    @Override
    public Integer call() throws Exception {
        Random random = new Random();
        int b=random.nextInt(11)+25;

        for (int i=1; i<=b; i++){
            System.out.println("Je suis F" + nbr + "" + i);
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){}
        }
        return b;
    }

    public static void main(String[] args) {

        //On initialise nos callable qui prenne le role d'un runnable
        TC e1 = new TC(1);
        TC e2 = new TC(2);
        TC e3 = new TC(3);

        //On initialise notre géstionaire de threads
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        //submit() au lieu de execute(). submit() return un objet de type Future afin de pouvoir acceder à l'execution des différents threads
        Future<Integer> f1 = executorService.submit(e1);
        Future<Integer> f2 = executorService.submit(e2);
        Future<Integer> f3 = executorService.submit(e3);

        //Tant que toujours actifs
        while (!f1.isDone() || !f2.isDone() || !f3.isDone()){
            System.out.println("attente");

            //On attend un certain temps en espérant que les threads seront tous finis. La boucle est là pour éviter les erreurs
            try {
                Thread.sleep(500);
            } catch (InterruptedException e){}

            //On récupère les solutions en utilisant les objets Future.
            try {
                System.out.println("f1 aaaaaaaaaaaaaa: "+f1.get()+" f2 : "+f2.get()+" f3 : "+f3.get());
            }
            catch (ExecutionException e){}
            catch (InterruptedException e){}
            executorService.shutdown();
        }
    }
}
