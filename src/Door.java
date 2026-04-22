public class Door {
    private int row, col, number;
    private boolean unlocked;
    private Puzzle puzzle;

    public Door(int r, int c, int num, Puzzle p) {
        row = r;
        col = c;
        number = num;
        puzzle = p;
        unlocked = false;
    }

    public int getRow() { return row; }
    public int getCol() { return col; }
    public int getNumber() { return number; }

    public boolean isUnlocked() { return unlocked; }
    public void unlock() { unlocked = true; }

    public Puzzle getPuzzle() { return puzzle; }
    public void setPuzzle(Puzzle p) { puzzle = p; }
}