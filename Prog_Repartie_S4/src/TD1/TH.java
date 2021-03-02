package TD1;

public class TH extends Thread {
    private Activite activite;

    public TH(int nb, int pause, int lng) {
        activite = new Activite(nb,pause,lng);
    }

    @Override
    public void run() {
        activite.faire();
    }

    public static void main(String[] args) {

        //On crée nos 3 threads (les trois processus fils qui vont etre crée par notre main)
        TH T1 = new TH(1,100,30);
        TH T2 = new TH(2,200,30);
        TH T3 = new TH(3,500,10);

        //getState() permet de connaitre l'état dans lequel est le thread en question
        System.out.println("Etat T1 : "+ T1.getState() + " Etat T2 : "+ T2.getState() + " Etat T3 : " + T3.getState());

        //start() va exécuter le thread en lancant sa méthode son run(). Les thread vont s'exécuter en parallèle de notre main
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
        System.out.println("Etat T1 : "+ T1.getState() + " Etat T2 : "+ T2.getState() + " Etat T3 : " + T3.getState());
    }
}
