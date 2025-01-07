package controller.player;

import model.player.Game;

import java.io.*;

public class SaveManager {

    private static final String SAVE_FILE = "game_save.dat";

    // Save the game state to a file
    public void saveGame(Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(game);
            System.out.println("Partie sauvegardée avec succès !");
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde de la partie : " + e.getMessage());
        }
    }

    // Load the game state from a file
    public Game loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (Game) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors du chargement de la partie : " + e.getMessage());
            return null;
        }
    }

    // Check if a save file exists
    public boolean saveExists() {
        File file = new File(SAVE_FILE);
        return file.exists();
    }

    // Optional: Delete the save file
    public void deleteSave() {
        File file = new File(SAVE_FILE);
        if (file.delete()) {
            System.out.println("Sauvegarde supprimée.");
        } else {
            System.out.println("Aucune sauvegarde à supprimer.");
        }
    }
}
