public interface Puzzle {
    String getPrompt();
    boolean attempt(String answer);
}