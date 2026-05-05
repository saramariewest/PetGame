# Pet Game

## What is it?

A small Java Swing game with a virtual pet that has hunger, thirst, mood, and energy levels.

## Features

- `Pet` class with four status values
- `Status` panel with progress bars for each value
- `Actions` panel with buttons: Feed, Drink, Play, Sleep
- `PetGame` main class builds the window

## Controls

- `Feed`: increases hunger by 20, max 100
- `Drink`: increases thirst by 20, max 100
- `Play`: increases mood by 20, decreases energy by 10
- `Sleep`: increases energy by 30, max 100

## Requirements

- Java JDK 8+ (Java Swing is part of the standard JDK)

## Run

- Compile: `javac src\*.java`
- Run: `java -cp src PetGame`

## Project structure

- `src/Pet.java`
- `src/Status.java`
- `src/Actions.java`
- `src/PetGame.java`
- `README.md`

## Development / ToDo

- Connect buttons to the `Pet` methods
- Update status bars from the `Pet` state
- Add automatic status decay over time
- Add graphics, sound, save/load

## Known issues

- Buttons are not functional yet
- Status panel is not linked to the pet data yet
