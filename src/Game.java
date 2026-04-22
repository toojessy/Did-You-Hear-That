import java.util.*;

public class Game {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GameMap map = new GameMap();
        Player player = new Player(1, 1);

        Entity entity = new Entity(-1, -1);
        boolean entitySpawned = false;

        Door[] doors = createDoors();

        while (true) {
            clearScreen();

            System.out.println("Doors unlocked: " + player.getDoorsUnlocked());
            System.out.println("Closet Creep speed: " + entity.getSpeed());
            System.out.println("Press Q to quit\n");

            map.printMap(player, doors, entity);

            System.out.print("\nMove (WASD or Q): ");
            String move = sc.nextLine().trim().toUpperCase();

            if (move.equals("Q")) {
                showQuitMessage();
                return;
            }

            movePlayer(player, move, map, entitySpawned, entity);

            checkDoors(player, doors);

            // Spawn after Door 1 unlocks, on the right side of the map
            if (!entitySpawned && doors[0].isUnlocked()) {
                entityReveal();
                entity.setPosition(13, 15);
                entitySpawned = true;
            }

            if (entitySpawned) {
                for (int i = 0; i < entity.getSpeed(); i++) {
                    entity.takeTurn(player, map, player.getScareLevel());

                    if (entity.getRow() == player.getRow()
                            && entity.getCol() == player.getCol()) {
                        showDeathScreen();
                        main(null);
                        return;
                    }
                }
            }

            if (map.getTile(player.getRow(), player.getCol()) == 'E') {
                if (player.getDoorsUnlocked() == doors.length) {
                    System.out.println("You escaped the Closet Creep...");
                    return;
                } else {
                    System.out.println("The exit is locked.");
                    pause();
                }
            }
        }
    }

    static void entityReveal() {
        clearScreen();
        System.out.println("...");
        sleep(700);

        clearScreen();
        System.out.println("           .-''''-.           ");
        System.out.println("         .'  _  _  '.         ");
        System.out.println("        /   (o)(o)   \\        ");
        System.out.println("       |      __      |       ");
        System.out.println("       |   .-'  '-.   |       ");
        System.out.println("        \\  \\_====_/  /        ");
        System.out.println("         '._/____\\_.'         ");
        System.out.println();

        System.out.println("THE CLOSET CREEP HAS FOUND YOU.");
        System.out.println("RUN.");
        pause();
    }

    static void movePlayer(Player player, String input, GameMap map, boolean entitySpawned, Entity entity) {
        int r = player.getRow();
        int c = player.getCol();

        if (input.equals("W")) {
            r--;
        } else if (input.equals("S")) {
            r++;
        } else if (input.equals("A")) {
            c--;
        } else if (input.equals("D")) {
            c++;
        } else {
            return;
        }

        if (r < 0 || r >= map.getRows() || c < 0 || c >= map.getCols()) {
            return;
        }

        if (map.getTile(r, c) != '#') {
            player.setPosition(r, c);

            if (map.getTile(r, c) == '!') {
                String id = "tile_" + r + "_" + c;

                if (!player.hasTriggeredEvent(id)) {
                    System.out.println("You hear something shifting in the dark...");
                    player.increaseScare();
                    player.markEventTriggered(id);

                    if (entitySpawned) {
                        entity.increaseSpeed();
                        System.out.println("The Closet Creep is getting faster.");
                    }

                    pause();
                }
            }
        }
    }

    static void checkDoors(Player player, Door[] doors) {
        for (Door door : doors) {
            if (player.getRow() == door.getRow()
                    && player.getCol() == door.getCol()
                    && !door.isUnlocked()) {

                System.out.println("\nDoor " + door.getNumber());
                System.out.println(door.getPuzzle().getPrompt());
                System.out.print("Your answer: ");
                String answer = sc.nextLine().trim();

                if (door.getPuzzle().attempt(answer)) {
                    door.unlock();
                    player.unlockDoor();
                    System.out.println("Unlocked!");
                } else {
                    System.out.println("Wrong... the Closet Creep is getting closer.");
                    player.setPosition(1, 1);
                    player.increaseScare();
                }

                pause();
            }
        }
    }

    static Door[] createDoors() {
        return new Door[] {
            new Door(
                1, 11, 1,
                new RiddlePuzzle(
                    "I am taken from a mine and shut inside a wooden case, from which I am never released, and yet I am used by almost every person. What am I?",
                    new String[] {"pencil lead", "lead", "graphite"}
                )
            ),
            new Door(
                4, 13, 2,
                new RiddlePuzzle(
                    "I can be measured, but I cannot be seen. I can be given away, but I still remain. What am I?",
                    new String[] {"time"}
                )
            ),
            new Door(
                8, 4, 3,
                new RiddlePuzzle(
                    "The one who has it does not tell it. The one who takes it does not know it. The one who knows it does not want it. What is it?",
                    new String[] {"counterfeit money", "counterfeit", "fake money"}
                )
            ),
            new Door(
                10, 11, 4,
                new RiddlePuzzle(
                    "I am lighter than what I am made of, and more of me is hidden than is seen. What am I?",
                    new String[] {"iceberg"}
                )
            ),
            new Door(
                12, 15, 5,
                new RiddlePuzzle(
                    "I have no voice, yet I can tell you everything. I have no life, yet I can die if not fed. What am I?",
                    new String[] {"fire"}
                )
            )
        };
    }

    static void showQuitMessage() {
        clearScreen();

        String[] messages = {
            "You walk away... but something follows.",
            "For now... you got away.",
            "The Closet Creep lets you go... this time.",
            "You escape. But you hear footsteps.",
            "You're safe. Probably.",
            "You run. You don't look back.",
            "The game ends. The feeling doesn't.",
            "You leave... but it knows where you are."
        };

        Random random = new Random();
        System.out.println(messages[random.nextInt(messages.length)]);
        System.out.println("\nPress ENTER to exit...");
        sc.nextLine();
    }

    static void showDeathScreen() {
        clearScreen();
        System.out.println("...");
        sleep(800);
        clearScreen();

        System.out.println("████████ ██   ██ ███████");
        System.out.println("   ██    ██   ██ ██     ");
        System.out.println("   ██    ███████ █████  ");
        System.out.println("   ██    ██   ██ ██     ");
        System.out.println("   ██    ██   ██ ███████");
        System.out.println();

        System.out.println(" ██████ ██      ██████ ███████ ████████");
        System.out.println("██      ██     ██    ████         ██   ");
        System.out.println("██      ██     ██    █████████    ██   ");
        System.out.println("██      ██     ██    ██     ██    ██   ");
        System.out.println(" ██████ ███████ ██████ ███████    ██   ");
        System.out.println();

        System.out.println(" ██████ ██████  ███████ ███████ ██████  ");
        System.out.println("██      ██   ██ ██      ██      ██   ██ ");
        System.out.println("██      ██████  █████   █████   ██████  ");
        System.out.println("██      ██   ██ ██      ██      ██      ");
        System.out.println(" ██████ ██   ██ ███████ ███████ ██      ");
        System.out.println();

        System.out.println(" ██████   ██████  ████████    ██    ██  ██████  ██    ██");
        System.out.println("██       ██    ██    ██        ██  ██  ██    ██ ██    ██");
        System.out.println("██   ███ ██    ██    ██         ████   ██    ██ ██    ██");
        System.out.println("██    ██ ██    ██    ██          ██    ██    ██ ██    ██");
        System.out.println(" ██████   ██████     ██          ██     ██████   ██████ ");
        System.out.println();

        System.out.println("“That didn’t go how you planned, did it?");
        System.out.println("Press ENTER to restart...");
        sc.nextLine();
    }

    static void pause() {
        System.out.println("\nPress ENTER...");
        sc.nextLine();
    }

    static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (Exception e) {
        }
    }

    static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}