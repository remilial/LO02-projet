package test;

import model.board.Board;
import model.board.SystemType;
import model.command.CommandType;
import model.player.strategy.AggressiveStrategy;
import model.player.strategy.RandomStrategy;
import org.junit.jupiter.api.Test;
import model.board.Sector;
import model.board.Ship;
import model.player.RealPlayer;
import model.player.BotPlayer;
import model.player.Player;

import static org.junit.Assert.assertNotNull;

public class BotPlayerTest {

    @Test
    public void testRandomStrategy() {
        BotPlayer bot = new BotPlayer("BotPlayer", new RandomStrategy());
        CommandType command = bot.getStrategy().determineNextCommand(bot);
        assertNotNull(command);  // Ensure a command is selected
    }

}
