# Did You Hear That?

A Java-based horror puzzle game where you must solve riddles, unlock doors, and escape before the Closet Creep catches you.

---

## Objective

Navigate through the map, solve riddles to unlock all 5 doors, and reach the exit.

Be careful — once you unlock the first door, **the Closet Creep begins chasing you** and gets faster as you trigger hidden scare tiles or make mistakes.

---

## Controls

- **W** → Move Up  
- **A** → Move Left  
- **S** → Move Down  
- **D** → Move Right  
- **Q** → Quit the game  

---

## Gameplay Features

- Unique riddles for each door (no repeats)
- The Closet Creep actively chases the player
- Increasing difficulty over time
- Hidden scare tiles that make the entity faster
- Progressive door system
- Custom ASCII death screen

---
## Known Issues

- ASCII visuals may appear misaligned depending on terminal size
- Entity movement is not perfectly optimal (uses simple pathfinding)
- Map layout is fixed and does not change between runs

---
## AI Use

AI tools (ChatGPT) were used to assist with:
- Debugging Java code
- Generating and refining game logic
- Creating riddles and ASCII visuals
- Writing and formatting the README

All code was reviewed, tested, and modified by the author.

---

## Run Commands (Quick Start)

If you already have the project downloaded and are inside the folder, just run:

```bash
cd Did-You-Hear-That
javac src/*.java
java -cp src Game
