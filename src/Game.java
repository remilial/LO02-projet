public class Game {
    // PLAYER
    // SECTOR
    // ROUND
    // PLAYER LIST
    Player p1, p2, p3;
    public void affichermenu(){
        System.out.println("Welcome to the game of Pocket Imperium");
        System.out.println("1.Would you like to play new game");
        System.out.println("2.Would you like to load recent game?");
        System.out.println("3.Would you like to view the rules");
        System.out.println("4.Exit game");
    }
    public void startGame(){
        System.out.println("Game Started");
    }
    public void loadGame(){
        System.out.println("Loading Game");
    }
    public void endGame(){
        System.out.println("See you next time");
    }
    public void viewRules() {
        System.out.println("Règles du jeu - Pocket Imperium");
        System.out.println("===================================");
        System.out.println("1. **Objectif du jeu** :");
        System.out.println("   - Le joueur qui a marqué le plus de points à la fin du jeu est le gagnant.");
        System.out.println("2. **Configuration du jeu** :");
        System.out.println("   - Chaque joueur choisit une couleur de vaisseau et reçoit un ensemble de cartes de commande.");
        System.out.println("   - Disposez les cartes de secteur dans une grille de 3x3, en plaçant la carte Tri-Prime au centre.");
        System.out.println("   - Chaque joueur place deux de ses vaisseaux sur un système de niveau I dans un secteur inoccupé.");
        System.out.println("3. **Phases du tour** : Chaque tour se compose de trois phases :");
        System.out.println("   - **Planifier** : Choisissez l'ordre de vos commandes (Expander, Explorer, Exterminer).");
        System.out.println("   - **Exécuter** : Révélez et exécutez vos commandes dans l'ordre : Expand, Explore, Exterminate.");
        System.out.println("   - **Exploiter** : Soutenez vos vaisseaux et choisissez les secteurs à marquer.");
        System.out.println("4. **Commandes** :");
        System.out.println("   - **Expand** : Ajoutez des vaisseaux aux systèmes que vous contrôlez.");
        System.out.println("   - **Explore** : Déplacez vos flottes de vaisseaux d'un hexagone à l'autre.");
        System.out.println("   - **Exterminate** : Invasion de systèmes adjacents contrôlés par d'autres joueurs.");
        System.out.println("5. **Fin du jeu** : Le jeu se termine après 20 tours ou si tous les vaisseaux d'un joueur sont éliminés.");
        System.out.println("   - Un décompte final des points est effectué pour déterminer le gagnant.");
        System.out.println("===================================");
        System.out.println("1. Return to Menu");
    }

}
