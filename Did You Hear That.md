# Game Proposal: *Did You Hear That?*

## Overview
*Did You Hear That?* is a console-based horror survival game developed in Java. The player must navigate a dark, maze-like environment while avoiding a pursuing creature that reacts to sound. The goal is to collect key items and escape before being caught.

---

## Concept
The game focuses on psychological tension rather than visual horror. Limited visibility, sound-based warnings, and unpredictable enemy movement create a suspenseful experience. The title reflects the core mechanic: the player must constantly question whether what they hear signals danger.

---

## Objectives
- Explore a grid-based map
- Locate and collect required items (e.g., keys)
- Unlock the exit
- Escape without being caught by the monster

---

## Game Design

### Map
- Represented as a 2D array (`char[][]`)
- Tile types:
  - `#` Wall
  - `.` Floor
  - `P` Player
  - `M` Monster
  - `K` Key
  - `E` Exit

### Visibility
- Only tiles near the player are displayed
- Enhances suspense and difficulty

---

## Gameplay Mechanics

### Player
- Moves using:
  - `W` (up)
  - `A` (left)
  - `S` (down)
  - `D` (right)
- Tracks:
  - Position
  - Inventory (keys, optional items)

### Monster
- Moves after each player action

#### Behavior:
- Moves randomly when far away
- Moves toward the player when noise is detected

---

## Sound System
Sound is a key gameplay element. The game provides text-based cues to indicate danger levels:

- Far: *"You think you heard something…"*
- Medium: *"Footsteps echo nearby."*
- Near: *"IT’S RIGHT BEHIND YOU."*

Some messages may be misleading to increase tension.

---

## Items
- **Key(s):** Required to unlock the exit

### Optional Items
- Batteries (increase visibility)
- Notes (add story/lore)

---

## Win / Lose Conditions

### Win
- Player reaches the exit tile (`E`) with required items

### Lose
- Monster reaches the player's position

---

## Game Loop
1. Display nearby map tiles
2. Accept player input
3. Update player position
4. Check for item collection
5. Move monster
6. Display sound cues
7. Check win/lose condition
8. Repeat

---

## Technical Implementation

### Data Structures
- `char[][]` for map representation
- `ArrayList` for item storage
- `HashMap` for inventory tracking

### Classes
- `Game` – main loop and control flow
- `Player` – player state and movement
- `Monster` – enemy behavior and AI
- `Map` – grid management and rendering
- `Item` – item definitions

---

## Future Enhancements
- Flashlight system with limited battery
- Advanced monster pathfinding
- Multiple levels/maps
- Save/load functionality
- Graphical interface using Java Swing

---

## Summary
This project demonstrates core Java concepts including object-oriented programming, data structures, and game logic. It provides an engaging and creative way to apply technical skills while building a playable horror experience.
