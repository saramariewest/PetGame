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
javac -d bin src\*.java
java -cp bin Main
```

On Linux or macOS:

```bash
mkdir -p bin
javac -d bin src/*.java
java -cp bin Main
```

## Notes

Compiled `.class` files are not part of the source code and are ignored through `.gitignore`. The `bin` folder is only used as the output folder when compiling the project.
