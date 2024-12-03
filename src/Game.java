import java.util.List;

public class Game {
    private List<Player> players;


    public void menu(){
        System.out.println("Welcome to Pocket Imperium");
    }
    public void startNewGame(){
        System.out.println("Starting new game...");
    }
    public void endGame(){
        System.out.println("Ending game...");
    }
    public void loadGame(){
        System.out.println("Loading game...");
    }
    public void viewRules(){
        System.out.println("Viewing rules...");
    }
}
