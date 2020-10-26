package fr.better.tools.config;

public class VariableConfig {

    private final String word, replaced;

    public VariableConfig(String word, String replaced) {
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
