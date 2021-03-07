#Compte rendu TD2

##Exercice 1

####Executor 
L'interface Executor nous permet de créer des executors concrets afin de pouvoir générer nos différents threads.
Le but est qu'à la place de créer notre thread à la main, on transmette un runnable à la fonction execute()
qui s'en chargera. execute() va aussi enregistrer le thread pour donner la possibilité d'interagir avec lui suivant
les fonctions développées dans l'executor concret.

##Exercice 2

####Question 2
Le thread parent donc le main s'exécute en premier et est fini avant que les fils ne commencent à travailler. Ainsi le main étant "fini"
se met en arrière-plan pour laisser la main aux fils. Mais puisque le main en arrière-plan n'est plus actif sans être réellement terminé
il ne peut pas savoir quand les fils ont fini. Même si c'était le cas puisque le main n'est plus actif il ne ferait rien.

####Question 3
La fonction Executors.newSingleThreadExecutor() permet de creer un gestionnaire (un executor concret) qui exécute un thread 
après l'autre.

La fonction Executors.newFixedThreadPool(n) permet de creer un gestionnaire qui execute les n threads en meme temps et non 
les uns apres les autres.

####Executor vs ExecutorService
ExecutorService possède des fonctions telle que shutdown() ou encore shutdownNow() que ne possède pas Executor.

####Question 6 / shutdown() vs shutdownNow()
shutdown() ferme l'entrée càd qu'il n'y a plus d'execute() possible. Ainsi il ne sera plus possible de lancer de nouveau 
threads. shutdown() va indiquer au main (thread parent) qu'il n'aura plus a gérer de threads à la fin de thread en cours.

shutdownNow() permet de demander l'interruption de l'exécution des tâches en cours et d'annuler l'exécution des tâches en 
attente. Ainsi dans notre cas il est plus intéressant d'utiliser shutdown() afin de laisser les threads en cours et en attente finir 
leurs exécutions.
 
 
##Exercice 3


####Runnable vs Callable
L'interface Runnable à sa methode run() est cette méthode return void, cette methode ne permet pas non plus de lancer des 
exceptions.

L'interface Callable<V> corrige ce problème puis que sa méthode call() peut retourner une valeur de type V. Cette méthode 
peut aussi lancer des exceptions.
 
####Future<V>
L'interface Future modélise des tâches qui s'exécutent dans un autre thread que le thread courant. C'est une sorte de fenetre sur 
l'execution d'un thread. C'est un objet qui permet d'instancier ce qui ce passe derrière l'execution d'un thread.
Future sert à savoir si l'exécution est terminée, à annuler l'execution de la tache et récupérer la valeur quand c'est fini ou quand 
le temps donné est écoulé.