package fr.better.tools.command.content;

import fr.better.tools.command.content.Action;

public abstract class Argument extends Action {
    public abstract String utility();

    public String getUtility(){
        String r = "";
        if (takePlayerAsParameter){
            r  = r + "[Joueur] ";
        }
        return r + utility();
    }

}
