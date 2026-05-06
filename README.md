# Pet Game

## Overview

A simple Java Swing pet game with status bars and action buttons.

## Features

- Feed the pet
- Give the pet water
- Play with the pet
- Let the pet sleep
- Pet stats decay over time

## Requirements

- Java 8 or higher
- Swing available (part of the standard JDK)

## Running

From the project root:

```bash
javac src/*.java
java -cp src Main
```

## Project structure

- `Main.java` — application entry point
- `PetGame.java` — creates the window, sprite, dashboard, and timer
- `Pet.java` — pet state and actions
- `PetDashboard.java` — combines status and actions panels
- `Actions.java` — buttons for feed/drink/play/sleep
- `Status.java` — progress bars for hunger/thirst/mood/energy
- `PetSprite.java` — placeholder pet display
