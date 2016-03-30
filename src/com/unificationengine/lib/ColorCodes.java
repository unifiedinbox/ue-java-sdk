package com.unificationengine.lib;

/**
 * Created by deadlock on 3/30/16.
 */
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Example Use
/*
 * Color code format WITH background color ->  :foreground,background:
 * Color code format WITHOUT background color -> :foreground,N:
 * Reset Color format -> [RC]
 *
 *   Example Use:
 *              String ansiColoredString = ColorCodes.ParseColors("Hello, This :blue,n:is[RC] a :red,white:response[RC].");
 *              - or -
 *              String ansiColoredString = ColorCodes.RED + "Hello" + ColorCodes.WHITE + ". This is a " + ColorColorCodes.BLUE + "test";
 */

/** Class used for ANSI Color manipulation in a console supporting ANSI color codes */
public class ColorCodes {

    public static final String RESET = "\u001B[0m";
    public static final String BLACK = "\u001B[30m";
    public static final String RED = "\u001B[31m";
    public static final String GREEN = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN = "\u001B[36m";
    public static final String WHITE = "\u001B[37m";

    /**
     * Parses a string with ANSI color codes based on the input
     * @param input the input string
     * @return the parsed ANSI string
     */
    public static String ParseColors(String input){
        String ret = input;
        Pattern regexChecker = Pattern.compile(":\\S+,\\S+:");
        Matcher regexMatcher = regexChecker.matcher(input);
        while(regexMatcher.find()){
            if(regexMatcher.group().length() != 0){
                String sub = regexMatcher.group().trim();
                sub = sub.replace(":", "");
                String[] colors = sub.split(",");

                ret = (colors[1].equalsIgnoreCase("N")) ?
                        ret.replace(regexMatcher.group().trim(), "\u001B[3" + getColorID(colors[0]) + ";1m")
                        :
                        ret.replace(regexMatcher.group().trim(), "\u001B[3" + getColorID(colors[0]) + ";4" + getColorID(colors[1]) + ";1m");
                ret = ret.replace("[RC]", ColorCodes.WHITE);
            }
        }
        ret = ret + ColorCodes.RESET; return ret;
    }

    /**
     * Internal function for getting a colors value
     * @param color The color as test
     * @return The colors integral value
     */
    private static int getColorID(String color){
        if(color.equalsIgnoreCase("BLACK")){
            return 0;
        }else if(color.equalsIgnoreCase("RED")){
            return 1;
        }else if(color.equalsIgnoreCase("GREEN")){
            return 2;
        }else if(color.equalsIgnoreCase("YELLOW")){
            return 3;
        }else if(color.equalsIgnoreCase("BLUE")){
            return 4;
        }else if(color.equalsIgnoreCase("MAGENTA")){
            return 5;
        }else if(color.equalsIgnoreCase("CYAN")){
            return 6;
        }else if(color.equalsIgnoreCase("WHITE")){
            return 7;
        }

        return 7;
    }


}