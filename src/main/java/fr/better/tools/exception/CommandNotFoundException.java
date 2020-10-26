package fr.better.tools.exception;

import fr.better.tools.exception.BetterException;

public class CommandNotFoundException extends BetterException {

    public CommandNotFoundException(Throwable cause) {
        super("You don't have set any command in your plugin.yml", cause);
    }
}
