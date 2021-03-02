package TD3;

import java.util.concurrent.*;

public class TC implements Callable<Matrix> {

    private Matrix A;
    private Matrix B;

    public TC(Matrix A, Matrix B ) {
        this.A=A;
        this.B=B;
    }

    @Override
    public Matrix call() throws Exception {
        return A.times(B);
    }
}


    /*public static void main(String[] args) {

        //On initialise nos callable qui prenne le role d'un runnable
        TC e1 = new TC(1,0);
        TC e2 = new TC(2, 7);
        TC e3 = new TC(3, 4);

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
                System.out.println("f1 : "+f1.get()+" f2 : "+f2.get()+" f3 : "+f3.get());
            }
            catch (ExecutionException e){}
            catch (InterruptedException e){}
            executorService.shutdown();
        }
    }*/