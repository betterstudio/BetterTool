package fr.better.tools.exception;

public class CommandNotFoundException extends BetterException {

    public CommandNotFoundException(Throwable cause) {
        super("You don't have set any command in your plugin.yml", cause);
    }
}
