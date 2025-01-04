package test;

import model.board.Board;
import model.board.Sector;
import model.board.Ship;
import model.board.SystemType;
import model.command.Command;
import model.command.CommandType;
import model.player.RealPlayer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CommandTest {

    @Test
    public void testExpandCommand() {
        RealPlayer player = new RealPlayer("Player1");
        Sector sector = new Sector(0, 0);
        player.setCurrentSector(sector);
        sector.setSystemType(SystemType.LEVEL1);  // Capacity for 2 ships

        Command expandCommand = new Command(CommandType.EXPAND, player);
        expandCommand.execute();

        assertEquals(1, sector.getShips().size());
    }

    @Test
    public void testExploreCommand() {
        RealPlayer player = new RealPlayer("Player1");
        Sector sector = new Sector(1, 1);
        player.setCurrentSector(sector);
        sector.setSystemType(SystemType.LEVEL2);  // Capacity for 3 ships
        Ship ship = new Ship("S1", "Red", player);
        sector.addShip(ship);
        player.addShip(ship);

        Command exploreCommand = new Command(CommandType.EXPLORE, player);
        exploreCommand.execute();

        assertEquals(0, sector.getShips().size());  // Ship should move to a neighboring hex
    }

    @Test
    public void testExterminateCommand() {
        RealPlayer player1 = new RealPlayer("Player1");
        RealPlayer player2 = new RealPlayer("Player2");

        Sector sectorA = Board.getInstance().getSector(0, 0);
        Sector sectorB = Board.getInstance().getSector(0, 1);  // Neighboring sector

        player1.setCurrentSector(sectorA);
        player2.setCurrentSector(sectorB);

        Ship ship1 = new Ship("S1", "Red", player1);
        Ship ship2 = new Ship("S2", "Blue", player2);

        sectorA.addShip(ship1);
        sectorB.addShip(ship2);

        player1.addShip(ship1);
        player2.addShip(ship2);

        Command exterminateCommand = new Command(CommandType.EXTERMINATE, player1);
        exterminateCommand.execute();

        assertEquals(0, sectorB.getShips().size(), "Enemy ship should be exterminated.");
    }

}

