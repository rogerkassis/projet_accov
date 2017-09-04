# projet_accov
Projet Accov ( Language de programmation JAVA)

Premièrement, exécuter : radar_serveur.java , et après un cliquer sur Lancer Radar.

Deuxièmement, exécuter : Lancer_avion.java
Puis pour chaque avion , il faut préciser le numéro de vol qui doit ëtre numérique. Puis Cliquer : Start.
Chaque avion commence à afficher les paramètres calculés (en utilisant la classe avion_avion.java)

Le radar commence à recevoir les informations des avions déja lancés.
Chaque avion envoi au radar les informations et le numéro de port que le controleur peut communiquer avec lui en l'utilisant.

Pour controler un avion: 
                          1 - remplir le numéro de vol dans l'interface du Radar, puis cliquer sur Contrleur.
                          2-  remplir les paramètres . 
                          3-  Cliquer sur connecter, une connection aura lieu enter le controleur et l'avion ayant ce numéro de vol                                     choisit.(si un autre contrleur veut communiquer avec le mème avion, il ne peut pas parce que le Thread du                                 premier controleur a blocker la fonction, et les fonctions utilisent "Synchronize").
                          4-  Cliquer sur Envoyer.
                          5-  Cliquer sur Déconnecté.(Maintenant un autre controleur peut communiquer avec cet avion)
                          
Si un avion envoi au Radar une vitesse < 200 ou une Altitude = 0 , un message s'affiche sur l'interface du Radar, avec le numéro 
de vol et les informations necessaires, en prenant la prioritée maximal du THread utilisé.
Pour tester cela, on peut envoyer en utilisant le controleur, une vitesse < 200 ou une altitude = 0 a un certain avion.



Remarques: Excuser moi parceque je n'ai pas eu le temps de faire tous les controls necessaires pour les (data entry) des informations,
comme les informations numériques et les la necessitée d'entrer les numéros de vol .... avant de faire le start de l'avion....

Merci.

                          

