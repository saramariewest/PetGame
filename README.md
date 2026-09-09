# Pet Game

## Description

Pet Game is a small Java Swing project. The player takes care of a virtual pet with values like hunger, thirst, mood, and energy. These values can be improved through actions and decrease over time.

The game also has a player with coins, an inventory, and a shop. Items can be bought in the shop and then used for feeding, drinking, or playing.

## Features

- Feed the pet
- Give the pet something to drink
- Play with the pet
- Let the pet sleep
- Pet values decrease automatically over time
- Highscore based on pet level and survival time
- Player earns coins regularly
- Buy items in the shop
- Show bought items in the inventory
- Different items have different prices and points

## Requirements

- Java JDK 26
- Maven 3.9 or newer (optional when compiling directly with the JDK)

## Run the game

Open `src/petgame/Main.java` in VS Code and run its `main` method.
Or run these commands from the project directory:

```powershell
mvn compile
java -cp target/classes petgame.Main
```

To compile without Maven:

```powershell
$javaFiles = Get-ChildItem -Path src/petgame -Recurse -Filter *.java
javac -encoding UTF-8 -d bin $javaFiles.FullName
java -cp bin petgame.Main
```

## Local save files

The game runs independently of MySQL and Spring Boot. `Main` creates a
`FileGameDataStore`, which saves games to `petgame.ser` and highscores to
`highscores.ser` in the project directory. No database connection is created.

`GameDataStore` describes the save and load operations used by the game;
it does not require a database.

## Separate database schema

[src/database/schema.sql](src/database/schema.sql) is kept as a standalone SQL
reference for MySQL Workbench. The game does not execute or load this file.
An existing schema on your MySQL server can remain in place for future learning.
MySQL Server and Workbench are not required to run the game.
