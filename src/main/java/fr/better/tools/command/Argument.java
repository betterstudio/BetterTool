package fr.better.tools.command;

public abstract class Argument extends Action{
    public abstract String utility();

    public String getUtility(){
        String r = "";
        if (takePlayerAsParameter){
            r  = r + "[Joueur] ";
        }
        return r + utility();
    }

}
