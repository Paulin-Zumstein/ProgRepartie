#Compte rendu TD4

##Exercice 1

####Question 1
On remarque un problème de synchronisation entre les deux threads dû au temps différent de leur tache respective.

####Question 2
synchronized permet de protéger l'objet qui possède la méthode synchronized pendant l'exécution de cette derniere. Ainsi
le carre correspond toujours à la valeur car l'objet est protégé lorsqu'il est utilisé. Mais on constate un légé décalage
entre le nombre de tour de boucle for et la valeur/carre affiché. Ce décalage n'est pas toujours effectif. 
Ce derniers et du au fait que le thread calcul n'attend pas que le thread affiche mais plutot que l'objet soit libre.
Ainsi il est possible selon le temps d'exection des 2 taches que l'une s'éxecute 2 fois d'affilé car l'objet est libre.

##Exercice 2

####Question 1 / 2
Chaque thread remet à 0 son jeton (semPerso.acquire()) fait ca tache et passe le jeton à son voisin (semVoisin.release()).

acquire() décrémente le(s) jeton(s) si il est > 0, fonction bloquante si < 0

release() incrémente le(s) jeton(s) 

Semaphore sem1 = new Semaphore(1, true); ----> La valeur true permet une gestion FIFO des tâches en attente sur ce sémaphore.

####Question 3
Si on met une seule place dans notre ExecutorService alors seul le premiers threads pourra s'exécuter. Puisque ce threads
attend que les suivants s'executent et que les suivantes ne pouvant pas rentrer dans le ExcutorService sont en attend de la 
fin du premiers threads, l'exécution se retrouve bloqué

####Question 4
semFin.acquir(3);  bloque la tache princpale tant que les trois thread on pas fait leur release;

##Exercice 3

####Question 1

Accès concurrentiel à l'attribut static chaineCommune donc certaines taches ce font manger