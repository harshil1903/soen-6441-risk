package com.risk.controller;

public class CommandParser {
    public static void mapCommandParser(String p_Action, String p_Arguments)
    {
        System.out.println("Inside Map Command Parser");
        System.out.println("Command : " + p_Action);
        System.out.println("Arguments : " + p_Arguments);

    }

    public static void gameCommandParser(String p_Action, String p_Arguments)
    {
        System.out.println("Inside Game Command Parser");
        System.out.println("Command : " + p_Action);
        System.out.println("Arguments : " + p_Arguments);
    }
}
