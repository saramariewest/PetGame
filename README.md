# Pet Game

## Description

Pet Game is a small Java Swing project. The player takes care of a virtual pet with values like hunger, thirst, mood, and energy. These values can be improved through actions and decrease over time.

The game also has a player with coins, an inventory, and a shop. Items can be bought in the shop and then used for feeding, drinking, or playing.

## Structure

- `src/app`: application entry point and game flow
- `src/model`: pet, player, items, and other core data
- `src/persistence`: save-game and highscore storage
- `src/ui`: Swing panels, menus, windows, and the game screen

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

- Java JDK, Java 17 or newer recommended
- No external libraries needed
- Swing is included in the JDK

## Run

From the project folder:

```powershell
New-Item -ItemType Directory -Force -Path bin
javac -d bin (Get-ChildItem -Recurse src -Filter *.java).FullName
java -cp bin Main
```

On Linux or macOS:

```bash
mkdir -p bin
javac -d bin $(find src -name "*.java")
java -cp bin Main
```
