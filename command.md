# Commands

Since the first commit, command system is implemented in library. 
On version 1.17-alpha.1, there are two types of commands:simple commands ( like for example ``/rtp`` ) who using the class ``SimpleCommand``
and complex commands (like for example ``/f <create/delete/info>`` ) who using the class ``ComplexCommand``.

## Complex Command

Complex Command are the main command's system.
It allows you to create your own subcommands by automatically generating the help message and eliminating errors and false manipulations.
First, create a ``Command`` object in your hand, and instantiate it with the function ``BetterPlugin#createComplexCommand``.

You can now add your own sub-arguments with the function ``Command#add``, this takes the name of the sub-command and its execution instance as a parameter.
