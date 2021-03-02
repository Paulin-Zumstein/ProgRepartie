package TD2;

public class TR implements Runnable{
    Activite activite;

    public TR(int id) {
        activite = new Activite(id,30);
    }

    public TR(int ident, int ps, int lg){
        activite = new Activite(ident, lg);
    }

    @Override
    public void run() {
        activite.faire();
    }

    public static void main(String[] args) {

        //Création de notre gestionnaire
        Exercice1Executor exercice1Executor = new Exercice1Executor();

        //On crée et envoi 15 runnable pour les exécuter simultanément
        for (int n=0; n<15; n++){
            TR r=new TR(n);
            exercice1Executor.execute(r);
        }

        System.out.println("main: les threads sont lances");
        System.out.println("Activites" + exercice1Executor.isActive());

        exercice1Executor.joinAll();
        System.out.println("main: fini");

        /*
        //On crée non objet runnable et ils possèdent la fonction run()
        TD1.TR e1 =new TD1.TR(1);
        TD1.TR e2 =new TD1.TR(2);
        TD1.TR e3 =new TD1.TR(3);

        //On crée des threads en donnant un objet runnable en paramètre. Le threads utilisara la méthode run de son objet runnable pour son exécution
        Thread T1 = new Thread(e1);
        Thread T2 = new Thread(e2);
        Thread T3 = new Thread(e3);
        System.out.println("Etat T1 : "+ T1.getState() + " Etat T2 : "+ T2.getState() + " Etat T3 : " + T3.getState());

        //On exécute les threads. start() va lancer la méthode run() de l'objet runnable du thread en question
        T1.start();
        T2.start();
        T3.start();
        System.out.println("main : T1, T2 et T3 sont lancé");

        //On affiche l'état de nos 3 threads 15 fois en attendant 200 milliseconds entre cahque affichage
        for(int k=0; k<15; k++){
            System.out.println("Execution T1 : "+ T1.getState() + " Execution T2 : "+ T2.getState() + " Execution T3 : " + T3.getState());

            //On fait dormir le processus du main pour laisser les threads avancer dans leurs exécutions.
            try{ Thread.sleep(200);
            }catch (InterruptedException e){}
        }

        System.out.println("main : terminé");

        //La méthode join va dire au thread courant càd notre main d'attendre que T1, T2 et T3 soit terminer pour passer à la suite. Car cette fonction est bloquante.
        try{ T1.join(); T2.join(); T3.join();
        }catch (InterruptedException e){}
        System.out.println("Etat T1 : "+ T1.getState() + " Etat T2 : "+ T2.getState() + " Etat T3 : " + T3.getState());*/
    }
}
