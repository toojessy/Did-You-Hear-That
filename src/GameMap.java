public class GameMap {
    private char[][] map;

    public GameMap() {
        map = new char[][] {
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'},
            {'#','P','.','.','.','#','.','.','.','.','.','1','.','.','.','!','#'},
            {'#','.','#','#','.','#','.','#','#','#','.','#','#','#','.','#','#'},
            {'#','.','.','#','.','.','.','#','.','.','.','.','.','#','.','.','#'},
            {'#','#','.','#','#','#','.','#','.','#','#','#','.','2','#','.','#'},
            {'#','.','.','.','.','#','.','.','.','#','.','.','.','#','.','.','#'},
            {'#','.','#','#','.','#','#','#','.','#','.','#','#','#','.','#','#'},
            {'#','.','.','#','.','.','.','#','.','.','.','#','.','.','.','!','#'},
            {'#','#','.','#','3','#','.','#','#','#','.','#','.','#','#','.','#'},
            {'#','.','.','#','.','#','.','.','.','#','.','#','.','.','#','.','#'},
            {'#','.','#','#','.','#','#','#','.','#','.','4','#','.','#','.','#'},
            {'#','.','.','.','.','.','.','#','.','.','.','#','.','.','.','.','#'},
            {'#','#','#','#','#','#','.','#','#','#','.','#','#','#','#','5','#'},
            {'#','!','.','.','.','.','.','.','.','#','.','.','.','.','.','E','#'},
            {'#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#','#'}
        };
    }

    public char getTile(int row, int col) {
        return map[row][col];
    }

    public int getRows() {
        return map.length;
    }

    public int getCols() {
        return map[0].length;
    }

    public void printMap(Player player, Door[] doors, Entity entity) {
        System.out.println("You are here: (" + player.getRow() + ", " + player.getCol() + ")");
        System.out.println();

        System.out.print("    ");
        for (int col = 0; col < map[0].length; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();

        for (int row = 0; row < map.length; row++) {
            System.out.printf("%2d  ", row);

            for (int col = 0; col < map[row].length; col++) {
                if (row == player.getRow() && col == player.getCol()) {
                    System.out.print(" P ");
                } else if (row == entity.getRow() && col == entity.getCol()) {
                    System.out.print(" ! ");
                } else if (isLockedDoor(row, col, doors)) {
                    System.out.print(" D ");
                } else if (isUnlockedDoor(row, col, doors)) {
                    System.out.print(" . ");
                } else {
                    char tile = map[row][col];

                    // Hide door-number markers
                    if (tile == '1' || tile == '2' || tile == '3' || tile == '4' || tile == '5') {
                        System.out.print(" . ");
                    }
                    // Hide scare tiles from the player
                    else if (tile == '!') {
                        System.out.print(" . ");
                    } else {
                        System.out.print(" " + tile + " ");
                    }
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Legend: P=You | !=Closet Creep | D=Door | E=Exit | #=Wall | .=Path");
    }

    private boolean isLockedDoor(int row, int col, Door[] doors) {
        for (Door door : doors) {
            if (door.getRow() == row && door.getCol() == col && !door.isUnlocked()) {
                return true;
            }
        }
        return false;
    }

    private boolean isUnlockedDoor(int row, int col, Door[] doors) {
        for (Door door : doors) {
            if (door.getRow() == row && door.getCol() == col && door.isUnlocked()) {
                return true;
            }
        }
        return false;
    }
}