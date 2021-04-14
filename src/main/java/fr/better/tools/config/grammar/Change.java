package fr.better.tools.config.grammar;

public class Change {

    private final String word, replaced;

    public Change(String word, String replaced) {
        this.word = word;
        this.replaced = replaced;
    }

    public String getWord() {
        return word;
    }

    public String getReplaced() {
        return replaced;
    }
}
