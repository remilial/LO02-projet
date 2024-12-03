import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Game game = new Game();
        boolean end = false;

        while (!end) {
            game.menu();
            int choice = sc.nextInt();
            if (choice == 1) {
                game.startNewGame();
            }
            else if (choice == 2) {
                game.loadGame();
            }
            else if (choice == 3) {
                game.viewRules();
            }
            else if (choice == 4) {
                game.endGame();
                end = true;
            }
            else {
                System.out.println("Invalid choice. Try again.");
            }
        }

    }
}
