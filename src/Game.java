import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class Game {
    Scanner sc = new Scanner(System.in);

    private static Game instance;

    boolean end = false;
    private String nameP1, nameP2, nameP3;
    List<Player> players = new ArrayList<Player>();

    public static Game getInstance() {
        if(instance == null){
            instance = new Game();
        }
        return instance;
    }

    public void startGame(){
        end = false;
        System.out.println("Welcome to the game of Pocket Imperium");
        System.out.println("1.Start new game");
        System.out.println("2.Load game");
        System.out.println("3.View rules of the game");
        System.out.println("4.Exit");
        while (!end) {
            int choice = sc.nextInt();
            if (choice == 1) {
                startNewGame();
            }
            else if (choice == 2) {
                loadGame();
            }
            else if (choice == 3) {
                viewRules();
            }
            else if (choice == 4) {
                endGame();
                end = true;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }

    }

    public void startNewGame(){
        System.out.println("Starting new game...");
        System.out.println("Effacement de la sauvegarde");
        System.out.println("How many real players:");
        int choice = sc.nextInt();
        if (choice == 1) {
            System.out.println("Name of PLayer1:");
            nameP1 = sc.next();
            nameP2 = "Bot1";
            nameP3 = "Bot2";
            Player player1 = new Player(nameP1,0);
            Player player2 = new Player(nameP2,0);
            Player player3 = new Player(nameP3,0);
        }
        else if (choice == 2) {
            System.out.println("Name of PLayer1:");
            nameP1 = sc.next();
            System.out.println("Name of PLayer2:");
            nameP2 = sc.next();
            nameP3 = "Bot";
            Player player1 = new Player(nameP1,0);
            Player player2 = new Player(nameP2,0);
            Player player3 = new Player(nameP3,0);
        }
        else if (choice == 3) {
            System.out.println("Name of PLayer1:");
            nameP1 = sc.next();
            System.out.println("Name of PLayer2:");
            nameP2 = sc.next();
            System.out.println("Name of PLayer3:");
            nameP3 = sc.next();
            Player player1 = new Player(nameP1,0);
            Player player2 = new Player(nameP2,0);
            Player player3 = new Player(nameP3,0);
        }
        else {
            System.out.println("Invalid choice. Try again.");
        }
    }

    public void endGame(){
        System.out.println("Exiting game...");
    }
    public void loadGame(){
        System.out.println("Loading game...");
    }
    public void viewRules(){
        System.out.println("Viewing rules...");
    }
}
