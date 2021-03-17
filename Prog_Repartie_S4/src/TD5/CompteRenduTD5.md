#Compte rendu TD5

##Exercice 1

####la boite aux lettres
Elle se charge de la gestion des accès concurrentiels de comme suivant :

Pour deposer un message il faut que le jeton du semaphore sDepot soit à 1. 

Pour lire un message il faut que le jeton du semaphore sRetrait soit à 1.

####Client
Le client est un thread qui se charge simplement de deposer un message dans la boite au lettre à l'aide la méthode deposeRequete()
de BalSimple. Ainsi le client ne s'occupe pas des accès concurrent.

####Serveur
Le serveur est constamment a l'écoute de part sa boucle While(true) et l'utilisation de la méthode retirerRequete() qui est 
bloquante.

##Exercice 2

####La boite aux lettres
C'est surtout le comportement de la boite aux lettres qui change paraport au premiers exercice. En effet à présent cette 
dernière utilise 3 semaphores pour eviter les accès concurrenciels.




##Exercice 3
voir photos