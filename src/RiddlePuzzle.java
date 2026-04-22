public class RiddlePuzzle implements Puzzle {
    private String prompt;
    private String[] answers;

    public RiddlePuzzle(String p, String[] a) {
        prompt = p;
        answers = a;
    }

    public String getPrompt() { return prompt; }

    public boolean attempt(String input) {
        input = input.toLowerCase();
        for (String a : answers) {
            if (input.contains(a)) return true;
        }
        return false;
    }
}