package fr.better.tools.command.base;

import fr.better.tools.config.BetterConfig;
import fr.better.tools.config.grammar.Change;
import org.bukkit.command.CommandExecutor;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;


public class BetterCommand {

    private String mainColor = "§6", secondColor = "§7";

    private Function<String, String> errorArgument = args -> "§cError : the specified argument are " + args,
            errorParameter = param -> "§cError : the real command is : " + param;

    public String getMainColor() {
        return mainColor;
    }

    public String getSecondColor() {
        return secondColor;
    }

    public Function<String, String> getErrorArgument() {
        return errorArgument;
    }

    public Function<String, String> getErrorParameter() {
        return errorParameter;
    }

    public static class MessageBuilder{

        private final BetterCommand command;

        public MessageBuilder(BetterCommand command){
            this.command = command;
        }

        public MessageBuilder loadAndSaveFromConfig(BetterConfig config){

            if(config.isString("Message.error.arguments")){
                command.errorArgument = args -> config.getMessage("Message.error.arguments", true, new Change("!args!", args));
            }else{
                config.set("Message.error.arguments", command.errorArgument.apply("!args!"));
            }

            if(config.isString("Message.error.parameters")){
                command.errorParameter = (cmd) -> config.getMessage("Message.error.parameters", true, new Change("!cmd!", cmd));
            }else{
                config.set("Message.error.parameters", command.errorParameter.apply("!cmd!"));
            }

            if(config.isString("Message.color.main")){
                command.mainColor = config.getMessage("Message.color.main");
            }else{
                config.set("Message.color.main", command.mainColor);
            }

            if(config.isString("Message.color.second")){
                command.secondColor = config.getMessage("Message.color.second");
            }else{
                config.set("Message.color.second", command.secondColor);
            }
            return this;
        }

        public MessageBuilder setArgumentError(Function<String, String> error){
            command.errorArgument = error;
            return this;
        }

        public MessageBuilder setParameterError(Function<String, String> error){
            command.errorParameter = error;
            return this;
        }

        public MessageBuilder setColor(String main, String second){
            command.mainColor = main;
            command.secondColor = second;
            return this;
        }
    }
}
