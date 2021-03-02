#COMPTE RENDU TD1

##Exercice 1

####Question 3 
Les programmes .java sont exécuté à l'aide de la commande java mais il faut d'abord les "compiler" avec la commande java pour la machine virtuelle javca qui se chargera d'exécuter le programme lors de la demande d'éxécution avec java.

####Question 4
Pour exécuter les trois programmes en meme temps il faudrait utiliser des threads afin de créer plusieurs processus qui s'éxecuteraient en meme temps. Ou alors il faudrait arriver à lancer l'exécution de ces trois processus au meme moment.

####Question 5
 La concurrence entre les processus est gérée à l'aide d'une file d'attente qui permet de distribuer un temps d'utilisation du CPU à chaque processus. Chaque processus passera certainement plusieur fois dans la file d'attente car le temps d'accès au CPU est limité. Il existe plusieurs methode de file d'attente comme le tourniquet ou la selection basée sur des priorités.
 

##Exercice 2

####join() :
La méthode join va dire au thread courant càd notre main d'attendre que T1, T2 et T3 soit terminer pour passer à la suite. Car cette fonction est bloquante.

####getState() :
Cette méthode permet de recupérer l'état dans lequel est un thread sous forme de String.

####Remarque exécution :
La répartition de l'utilisation du CPU semble équilibré car le thread T1 qui a le moins de temps à attendre termine bien en premier et ainsi de suite pour T3 et T2.

##Exercice 3

voir code.

####yield() :
La méthode yield() applique une pause à l'exécution du thread courant, afin de libérer le processeur pour d'autres unités d'exécution en attente. La méthode rend disponible le CPU puis attend qu'il soit libre pour le récupérer. Avec yield des que le CPU est libre la fonction le récupère immédiatement. 