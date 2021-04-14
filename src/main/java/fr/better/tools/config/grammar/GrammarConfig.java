package fr.better.tools.config.grammar;

public enum GrammarConfig {

    ACCENT_E_1("é", "!e"),
    ACCENT_E_2("è", "/e"),
    ACCENT_E_3("ê", "%e"),
    ACCENT_A("à", "!a"),
    ACCENT_U("ù", "!u"),

    SPECIAL_1("»", "!1"),
    SPECIAL_2("«", "!2"),
    SPECIAL_3("│", "!3"),
    SPECIAL_4("❌", "!4"),
    SPECIAL_5("✔", "!5"),
    SPECIAL_6("•", "!6");

    private final String letter;
    private final String code;

    GrammarConfig(String letter, String code) {
        this.letter = letter;
        this.code = code;
    }

    public String replace(String message) {
        return message.replace(code, letter);
    }
}
