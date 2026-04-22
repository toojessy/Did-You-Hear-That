/**
 * Talon
 * Course: Java I
 * Date: 04/20/2026
 *
 * Represents a one-time scare event on the map.
 */
public class ScareEvent {
    private String id;
    private int row;
    private int col;
    private String message;

    public ScareEvent(String id, int row, int col, String message) {
        this.id = id;
        this.row = row;
        this.col = col;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getMessage() {
        return message;
    }
}