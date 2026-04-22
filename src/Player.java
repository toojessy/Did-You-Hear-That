import java.util.HashSet;
import java.util.Set;

/**
 * Represents the player and stores position, progress, and scare state.
 */
public class Player {
    private int row;
    private int col;
    private int doorsUnlocked;
    private int scareLevel;

    // Tracks tiles/events already triggered so they don't repeat
    private Set<String> triggeredEvents;

    public Player(int row, int col) {
        this.row = row;
        this.col = col;
        this.doorsUnlocked = 0;
        this.scareLevel = 0;
        this.triggeredEvents = new HashSet<>();
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getDoorsUnlocked() {
        return doorsUnlocked;
    }

    public int getScareLevel() {
        return scareLevel;
    }

    public void setPosition(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void unlockDoor() {
        doorsUnlocked++;
    }

    public void increaseScare() {
        scareLevel++;
    }

    // 👇 NEW: Prevents repeating scare triggers
    public boolean hasTriggeredEvent(String eventId) {
        return triggeredEvents.contains(eventId);
    }

    public void markEventTriggered(String eventId) {
        triggeredEvents.add(eventId);
    }
}