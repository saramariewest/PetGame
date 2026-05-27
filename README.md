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

## Project Structure

- `Main.java` starts the program.
- `PetGame.java` creates the main window and the timer.
- `Pet.java` stores the pet values and actions.
- `Player.java` stores the player's coins and inventory.
- `Items.java` contains the available shop items.
- `Type.java` separates items into Food, Drink, and Toy.
- `PetDashboard.java` connects the status display, buttons, and supply area.
- `PetActions.java` contains the action buttons.
- `PetStats.java` shows hunger, thirst, mood, and energy.
- `PlayerStats.java` shows the player's coins.
- `PetSupplies.java` contains the buttons for inventory and shop.
- `PetInventory.java` shows bought items.
- `PetShop.java` allows the player to buy new items.
- `PetSprite.java` is currently a placeholder for the pet graphic.

## Learning Goals

This project is mainly used to practice these basics:

- Classes and objects in Java
- Simple Swing components
- `JFrame`, `JPanel`, `JButton`, `JLabel`, and `JProgressBar`
- `ActionListener` for button clicks
- `Timer` for repeated game actions
- `Map` for a simple inventory
- Splitting a program into several classes

## Notes

Compiled `.class` files are not part of the source code and are ignored through `.gitignore`. The `bin` folder is only used as the output folder when compiling the project.
