package com.github.laefye.pixelbattle;

public class SomeConstants {
    public static final int PALLETE_SLOT = 4;
    public static final int BOMB_SLOT = 5;
    public static final long DELAY = 15000;
    public static int MAX_BONUS_IN_STACK = 5;

    public static final String PLUGIN_FOLDER = "plugins/PixelBattle/";
    public static final String MEMBERS_FOLDER = PLUGIN_FOLDER + "members/";

    public static String stringifyInteger(int n) {
        if (n < 1000) {
            return String.valueOf(n);
        }
        if (n < 1000000) {
            return n + "k";
        }
        return n + "kk";
    }
}
