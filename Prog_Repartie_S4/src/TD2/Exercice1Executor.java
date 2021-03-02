package TD2;

import java.util.Vector;
import java.util.concurrent.Executor;

public class Exercice1Executor implements Executor {

    //Vector c'est une structure de donnée genre Arraylist
    Vector<Thread> threads;

    public Exercice1Executor() {
        this.threads = new Vector<>();
    }

    //Ici on récupère un un Runnable pour en faire un thread. Notre executor concret va permettre la gestion de ces derniers
    public void execute(Runnable r){

        //On crée un nouveau thread dédié au runnable qui nous a été transmit et on ajoute le thread notre liste
        threads.add(new Thread(r));
        threads.lastElement().start();
    }

    //La fonction permet de savoir si il y a encore un ou plusieurs threads actif
    public boolean isActive(){
        for (Thread t : threads){
            if (t.isAlive()){
                return true;
            }
        }
        return false;
    }

    //La fonction join() permettant d'attendre la fin de l'execution d'un thread, l'idée ici est t'attendre que la totalité des threads on fini leurs exécutions
    public void joinAll(){
        for (Thread t : threads){
            try{
                t.join();
            }
            catch (InterruptedException e){
                System.out.println(e.getMessage());
            }
        }
    }
}