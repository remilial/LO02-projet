package fr.lo02.imperium_control.controller;

import fr.lo02.imperium_control.model.board.Board;
import fr.lo02.imperium_control.model.player.Game;
import fr.lo02.imperium_control.model.player.Player;

import java.io.*;
import java.util.List;

public class SaveManager {

    private static final String SAVE_FILE_PATH = "pocket_imperium_save.dat";
    private static final String SAVE_FILE = "savegame.ser"; // File name for the saved game

    // Save the Game object to a file
    public void saveGame(Game game) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(SAVE_FILE))) {
            oos.writeObject(game);
            System.out.println("Game saved successfully!");
        } catch (IOException e) {
            System.err.println("Failed to save the game: " + e.getMessage());
        }
    }

    /**
     * Loads the game state from a file.
     *
     * @return An object array containing the loaded board and players.
     */
    // Load the Game object from the file
    public Game loadGame() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(SAVE_FILE))) {
            return (Game) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("No saved game found.");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Failed to load the game: " + e.getMessage());
        }
        return null;
    }

    /**
     * Deletes the save file.
     */
    public static void deleteSaveFile() {
        File file = new File(SAVE_FILE_PATH);
        if (file.exists() && file.delete()) {
            System.out.println("Save file deleted successfully.");
        } else {
            System.err.println("Error deleting save file or no save file found.");
        }
    }
}
