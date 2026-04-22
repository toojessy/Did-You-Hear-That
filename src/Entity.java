import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Entity {
    private int row;
    private int col;
    private int speed;

    public Entity(int row, int col) {
        this.row = row;
        this.col = col;
        this.speed = 2; // starts faster
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getSpeed() {
        return speed;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void increaseSpeed() {
        if (speed < 4) {
            speed++;
        }
    }

    public void takeTurn(Player player, GameMap map, int scareLevel) {
        int distance = getManhattanDistance(player);

        if (distance <= 4) {
            moveGreedyToward(player, map);
        } else if (distance <= 8 || scareLevel >= 2) {
            if (Math.random() < 0.9) {
                moveGreedyToward(player, map);
            } else {
                moveRandom(map);
            }
        } else {
            moveRandom(map);
        }
    }

    public void moveRandom(GameMap map) {
        List<int[]> directions = new ArrayList<>();
        directions.add(new int[] {-1, 0});
        directions.add(new int[] {1, 0});
        directions.add(new int[] {0, -1});
        directions.add(new int[] {0, 1});

        Collections.shuffle(directions);

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (canMoveTo(newRow, newCol, map)) {
                row = newRow;
                col = newCol;
                return;
            }
        }
    }

    private void moveGreedyToward(Player player, GameMap map) {
        List<int[]> candidateMoves = new ArrayList<>();
        candidateMoves.add(new int[] {-1, 0});
        candidateMoves.add(new int[] {1, 0});
        candidateMoves.add(new int[] {0, -1});
        candidateMoves.add(new int[] {0, 1});

        int bestDistance = Integer.MAX_VALUE;
        int bestRow = row;
        int bestCol = col;

        for (int[] move : candidateMoves) {
            int newRow = row + move[0];
            int newCol = col + move[1];

            if (!canMoveTo(newRow, newCol, map)) {
                continue;
            }

            int distance = Math.abs(player.getRow() - newRow)
                    + Math.abs(player.getCol() - newCol);

            if (distance < bestDistance) {
                bestDistance = distance;
                bestRow = newRow;
                bestCol = newCol;
            }
        }

        row = bestRow;
        col = bestCol;
    }

    private boolean canMoveTo(int newRow, int newCol, GameMap map) {
        if (newRow < 0 || newRow >= map.getRows() || newCol < 0 || newCol >= map.getCols()) {
            return false;
        }

        char tile = map.getTile(newRow, newCol);
        return tile != '#';
    }

    private int getManhattanDistance(Player player) {
        return Math.abs(player.getRow() - row) + Math.abs(player.getCol() - col);
    }
}