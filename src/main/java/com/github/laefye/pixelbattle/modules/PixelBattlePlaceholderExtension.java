package com.github.laefye.pixelbattle.modules;

import com.github.laefye.pixelbattle.Canvas;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PixelBattlePlaceholderExtension extends PlaceholderExpansion {
    private final String author;
    private final String version;
    private final PixelBattlePlugin plugin;

    public PixelBattlePlaceholderExtension(PixelBattlePlugin plugin) {
        this.plugin = plugin;
        var description = PixelBattlePlugin.getInstance().getDescription();
        author = description.getAuthors().get(0);
        version = description.getVersion();
    }

    @Override
    public @NotNull String getIdentifier() {
        return "pixelbattle";
    }

    @Override
    public @NotNull String getAuthor() {
        return author;
    }

    @Override
    public @NotNull String getVersion() {
        return version;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        if (params.equalsIgnoreCase("top_1")) {
            return plugin.getTopList().get(0);
        }
        if (params.equalsIgnoreCase("top_2")) {
            return plugin.getTopList().get(1);
        }
        if (params.equalsIgnoreCase("top_3")) {
            return plugin.getTopList().get(2);
        }
        var member = plugin.getMember(player);
        if (player == null || member == null) {
            return "";
        }
        if (params.equalsIgnoreCase("time")) {
            if (plugin.getCanvas().getMode() == Canvas.Mode.View) {
                return "View";
            } else if (member.allowPlace()) {
                return "You can build";
            } else {
                return "In " + (member.getTime() / 1000) + " seconds";
            }
        }
        if (params.equalsIgnoreCase("placed")) {
            return String.valueOf(member.getPlaced());
        }
        return "";
    }
}
