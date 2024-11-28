import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        int cas = 0;
        Game game = new Game();
        Scanner sc = new Scanner(System.in);

        while(cas!=99){
            switch(cas) {
                    case 0:
                        game.menu();
                    int choix = sc.nextInt();
                    if(choix == 1){cas = 1;}
                    else if(choix == 2){cas = 2;}
                    else if(choix == 3){cas = 3;}
                    else if(choix == 4){cas = 98;}
                    else {System.out.println("Réesayer");}
                    break;

                    case 1: //New Game
                        game.startGame();
                    break;

                    case 2: //Load Game
                        System.out.println("Loading Game");
                    break;

                    case 3: //View rules
                        game.viewRules();
                        int choix2 = sc.nextInt();
                        if(choix2 == 1){cas = 0;}
                        else {System.out.println("Réesayer");}
                    break;

                    case 98: // Exit Game
                        game.endGame();
                        cas = 99;
                    break;

                    default:
                    System.out.println("Option invalide, veuillez réessayer.");
                    break;
            }
        }
        sc.close();
    }
}
