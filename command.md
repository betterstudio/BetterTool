# Commands

Since the first commit, command system is implemented in library. 
On version 1.17-alpha.1, there are two types of commands:simple commands ( like for example ``/rtp`` ) who using the class ``SimpleCommand``
and complex commands (like for example ``/f <create/delete/info>`` ) who using the class ``ComplexCommand``.

## Complex Command

Complex Command are the main command's system.
It allows you to create your own subcommands by automatically generating the help message and eliminating errors and false manipulations.
First, create a ``Command`` object in your hand, and instantiate it with the function ``BetterPlugin#createComplexCommand``.

You can now add your own sub-arguments with the function ``Command#add``, this takes the name of the sub-command and its execution instance as a parameter.

```java

public void onStart(){
  Command command = createComplexCommand("test");
  command.add("underCommand", new UnderCommandArgument());
}

```

The abstract class Argument is needed. The function ``Argument#utility()`` is for the command's utility in the help message. The functiopn ``Argument#parameter`` define the parameters in the help message AND he parameters essential to the execution of the ducoup command, you must respect a graphic charter: for example `` <player> [size] ``, here the argument `` <player> `` is essential and must be specified otherwise there will be an error. On the other hand, the argument `` [size] `` is optional.

```java

public class AddArgument extends Argument {

    @Override
    public String utility() {
        return "<enter the utility of the command, who is used in the help message>";
    }

    @Override
    public String execute(Player player, List<String> parameters) {
        // Do what you want when someone execute the command
        return "<return the message sended to the player here>";
    }

    @Override
    public String parameter() {
        //<> for essentials args
        //[] for optional args
        return "<args1> <args2> [args3]";
    }
}
```

You could arleady use the function ``Argument#permission()`` to define permission needed to this arg.















