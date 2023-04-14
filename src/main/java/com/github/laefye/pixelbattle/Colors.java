package com.github.laefye.pixelbattle;

import org.bukkit.Material;

public class Colors {
    public static Material getMaterial(Color color) {
        switch (color) {
            case WHITE -> {
                return Material.WHITE_CONCRETE;
            }
            case ORANGE -> {
                return Material.ORANGE_CONCRETE;
            }
            case MAGENTA -> {
                return Material.MAGENTA_CONCRETE;
            }
            case LIGHT_BLUE -> {
                return Material.LIGHT_BLUE_CONCRETE;
            }
            case YELLOW -> {
                return Material.YELLOW_CONCRETE;
            }
            case LIME -> {
                return Material.LIME_CONCRETE;
            }
            case PINK -> {
                return Material.PINK_CONCRETE;
            }
            case GRAY -> {
                return Material.GRAY_CONCRETE;
            }
            case LIGHT_GRAY -> {
                return Material.LIGHT_GRAY_CONCRETE;
            }
            case CYAN -> {
                return Material.CYAN_CONCRETE;
            }
            case PURPLE -> {
                return Material.PURPLE_CONCRETE;
            }
            case BLUE -> {
                return Material.BLUE_CONCRETE;
            }
            case BROWN -> {
                return Material.BROWN_CONCRETE;
            }
            case GREEN -> {
                return Material.GREEN_CONCRETE;
            }
            case RED -> {
                return Material.RED_CONCRETE;
            }
            case BlACK -> {
                return Material.BLACK_CONCRETE;
            }
        }
        return Material.AIR;
    }

    public static Color getColor(Material material) {
        switch (material) {
            case WHITE_CONCRETE -> {
                return Color.WHITE;
            }
            case ORANGE_CONCRETE -> {
                return Color.ORANGE;
            }
            case MAGENTA_CONCRETE -> {
                return Color.MAGENTA;
            }
            case LIGHT_BLUE_CONCRETE -> {
                return Color.LIGHT_BLUE;
            }
            case YELLOW_CONCRETE -> {
                return Color.YELLOW;
            }
            case LIME_CONCRETE -> {
                return Color.LIME;
            }
            case PINK_CONCRETE -> {
                return Color.PINK;
            }
            case GRAY_CONCRETE -> {
                return Color.GRAY;
            }
            case LIGHT_GRAY_CONCRETE -> {
                return Color.LIGHT_GRAY;
            }
            case CYAN_CONCRETE -> {
                return Color.CYAN;
            }
            case PURPLE_CONCRETE -> {
                return Color.PURPLE;
            }
            case BLUE_CONCRETE -> {
                return Color.BLUE;
            }
            case BROWN_CONCRETE -> {
                return Color.BROWN;
            }
            case GREEN_CONCRETE -> {
                return Color.GREEN;
            }
            case RED_CONCRETE -> {
                return Color.RED;
            }
            case BLACK_CONCRETE -> {
                return Color.BlACK;
            }
        }
        return null;
    }
}
