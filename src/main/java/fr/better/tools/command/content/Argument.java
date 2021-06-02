package fr.better.tools.command.content;

import fr.better.tools.command.base.ParticularityType;
import fr.better.tools.command.content.Action;

public abstract class Argument extends Action {
    public abstract String utility();

    public String getUtility(boolean console){
        String prefix = console ? " <player> " : " [player] ";
        return (type == ParticularityType.TAKE_PLAYER_AS_ARG || type == ParticularityType.NEED_PLAYER_AS_ARG ? prefix : " " ) + utility();
    }
}
