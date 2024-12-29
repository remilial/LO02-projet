PocketImperium/
│
├── src/
│   ├── controller/
│   │   ├── GameController.java
│   │   ├── SaveManager.java
│   │   └── CommandHandler.java
│   │
│   ├── model/
│   │   ├── board/
│   │   │   ├── Board.java
│   │   │   ├── Sector.java
│   │   │   ├── Hex.java
│   │   │   └── SystemType.java
│   │   │
│   │   ├── player/
│   │   │   ├── Player.java
│   │   │   ├── RealPlayer.java
│   │   │   ├── BotPlayer.java
│   │   │   └── Strategy.java
│   │   │
│   │   ├── command/
│   │   │   ├── Command.java
│   │   │   └── CommandType.java
│   │   │
│   │   └── Game.java
│   │
│   ├── view/
│   │   ├── cli/
│   │   │   └── ConsoleView.java
│   │   └── gui/
│   │       ├── GuiView.java
│   │       └── GameEventHandlers.java
│   │
│   └── Main.java
│
└── test/
├── GameTest.java
├── PlayerTest.java
└── SectorTest.java
1. Controller Layer (controller/)
   Manages the game flow, input processing, and interaction between model and view.
2. Model Layer (model/)
   Represents the game logic, including the board, players, commands, and game rules.
3. View Layer (view/)
   Displays game state (console or GUI), providing feedback and user interaction.
4. Main Class (Main.java)
   Entry point of the application. Initializes the game and launches the controller.
5. Test Layer (test/)
   Contains JUnit test cases for critical components.

GameController	Oversees the main game loop, coordinates phases (Plan, Perform).
SaveManager	Handles saving/loading of game states (serialization).
CommandHandler	Processes player/bot commands during game phases.
Game	Central class managing overall game state and progression.
Board	Represents the 3x3 game grid with sectors.
Sector	Contains Hex objects, controls ships, and system levels.
Hex	Represents individual hexagonal tiles in sectors.
SystemType (enum)	Enum representing system levels (I, II, III).
Player	Abstract class for player data (name, ships, points).
RealPlayer	Represents human players, handles manual input.
BotPlayer	AI-controlled player using predefined strategies.
Strategy (interface)	Interface for AI strategies (Expand, Explore, Exterminate).
Command	Represents individual player commands.
CommandType (enum)	Enum for command types (EXPAND, EXPLORE, EXTERMINATE).
ConsoleView	Displays the game in the terminal (CLI interface).
GuiView	Swing/JavaFX interface for graphical representation.
GameEventHandlers	Handles GUI interactions and forwards them to the controller.
GameTest	Tests overall game functionality.
PlayerTest	Tests player actions, scoring, and commands.
SectorTest	Tests sector and hex interactions.
*************************************************************************************************
Workload Division:
First Person (Core Game Logic & Model Implementation)
Focus: Game Mechanics, Rules, and Back-End Systems
Tasks:

Model Layer (Game Core)

Game.java – Implement the game loop, phases (Plan, Perform, Exploit).
Board.java – Initialize and manage the game grid (3x3 sectors).
Sector.java – Represent sectors with system levels (I, II, III).
Hex.java – Handle individual tiles (hexes) in each sector.
SystemType.java – Create an enum for system levels (I, II, III).
Players and Strategies

Player.java – Abstract base class for all players.
RealPlayer.java – Manage input and actions for human players.
BotPlayer.java – Implement AI logic for virtual players.
Strategy.java – Develop AI strategies (Expand, Explore, Exterminate).
Commands and Actions

Command.java – Represents player actions.
CommandType.java – Enum defining available commands.
Testing (JUnit)

Unit tests for Game, Player, and Sector classes.