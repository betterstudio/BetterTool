package fr.better.tools.command.abstraction;

public interface Parameter{
    default String utility(){ return "don't have any utility defined."; }
    default String parameter(){ return "don't have any parameter defined."; }
    default int parameterSize(){ return parameter().split(" ").length; }
}