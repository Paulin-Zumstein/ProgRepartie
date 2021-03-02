package TD1;

public class Exercice1 {
    private String nbr;

    Exercice1(String nbr){
        this.nbr=nbr;
    }

    public void coco(){

        //pour chaque tour on affiche i puis on dort
        for (int i=0; i<100; i++){
            System.out.println("le processus" + nbr + " et le compteur est a " + i);

            //try permet de récupérer les signaux InterruptedException pour stopper le dodo
            try {
                Thread.sleep(500); //milliseconds
            }catch (InterruptedException e){}
        }
    }

    public static void main(String[] args) {

        //on regarde si notre tableau d'argument n'est pas vide ou trop remplit
        if(args.length!=1){

            //si probleme d'rgument on previent l'utilisateur et on stop l'execution
            System.out.println("probleme d'argument");
            System.exit(1);
        }

        //Si le bon nombre d'argument on exécute coco
        Exercice1 exercice1= new Exercice1(args[0]);
        exercice1.coco();
    }
}
