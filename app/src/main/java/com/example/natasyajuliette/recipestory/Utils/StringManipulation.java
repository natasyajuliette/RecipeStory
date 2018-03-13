package com.example.natasyajuliette.recipestory.Utils;

/**
 * Created by natasyajuliette on 13/03/18.
 */

public class StringManipulation {

    public static String expandUsername(String username) {
        return username.replace(".", " ");
    }

    public static String condensedUsername(String username) {
        return username.replace(" ", ".");
    }
}
