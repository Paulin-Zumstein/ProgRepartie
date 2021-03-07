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



#a finir