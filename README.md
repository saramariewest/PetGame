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
- Maven 3.9 or newer
- MySQL Server 8.0 or newer for the MySQL version
- MySQL Workbench for creating and inspecting the database

## Run with MySQL

1. Open [database/schema.sql](database/schema.sql) in MySQL Workbench and run it. This creates the `petgame` database and its tables.
2. Set your local MySQL credentials in PowerShell. These values are only available in your current terminal session:

```powershell
$env:PETGAME_DB_USERNAME = "root"
$env:PETGAME_DB_PASSWORD = "your-password"
```

3. Start the Spring Boot application with the MySQL profile:

```powershell
mvn spring-boot:run "-Dspring-boot.run.profiles=mysql"
```

The default database URL is `jdbc:mysql://localhost:3306/petgame?serverTimezone=Europe/Berlin`.
To use another server or port, set `PETGAME_DB_URL` before starting the application.

## How the MySQL version works

- `SaveGameEntity` maps one save slot to the `save_games` table.
- `InventoryEntryEntity` maps each owned item and its quantity to `inventory_entries`.
- `HighscoreEntity` maps highscores to the `highscores` table.
- `MySqlGameDataStore` implements the existing `GameDataStore` interface, so the Swing UI can save and load without knowing whether the data comes from files or MySQL.

After creating or saving a game, run this in MySQL Workbench to inspect the saved slots:

```sql
USE petgame;
SELECT id, save_name, pet_name, level, coins, saved_at
FROM save_games;
```
