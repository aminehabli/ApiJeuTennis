# ApiJeuTunis
La structure de base du code respecte l'architecture hexagonale. Le coeur de l'application se trouve dans le package domain.

L'interface GameService définis le port pour interagir avec le domaine. 

L'interface ScoreRepository définis le port out.

La classe InMemoryScoreRepository est une implementation pour l'interface ScoreRepository qui stocke les scores en mémoire.
L'implémentation est découplée du domaine métier ce qui facilite ultérieur par une autre implémentation qu'une base de données sans affecter la logique métier.

Dans le package application, on définit l'implémentation du service GameService

La classe ConsoleAdapter est un adpatateur console d'entrée qui fait reference à l'interface GameService qui donne à l'utilisateur final d'interagir pour jouer avec la console.

Le jeu est modélisé par une Classe Game gère le déroulement du jeu qui contient deux Tennis players et lorsqu'on commence le jeu
, une instance de la classe Score est creé.

Un TennisPlayer représente le joueur quia un nom pour distinguer entre les deux joueurs.

Le score a un tableau des scores possibles et une fonction
pour incrementer le score selon le player qui gagne le point.

Au niveau de cette classe, j'ai mis les fonctions qui couvrent les règles de base du tennis y compris isDeuce, hasAdvantage,
hasWinner, qui mène le match getLeadingPlayer et la description du score.

Pour utiliser ce code, vous pouvez créer une instance du GameServiceImpl qui se charge de créer une instance de game 
et appeler pointWonBy() pour chaque point marqué puis getCurrentScore() pour obtenir le score actuel.

GameServiceImplTest : est la classe de test du service GamedServiceImpl
