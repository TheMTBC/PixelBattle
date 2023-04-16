package com.github.laefye.pixelbattle;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class LangConfig {

    private final PixelBattlePlugin plugin;
    private final File configFile;
    private YamlConfiguration config;

    public LangConfig(PixelBattlePlugin plugin) {
        this.plugin = plugin;
        this.configFile = new File(plugin.getDataFolder(), "lang.yml");
    }

    public void loadConfig() {
        if (!configFile.exists()) {
            plugin.saveResource("lang.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public String getString(String path) {
        if (!config.contains(path)) {
            return path;
        }
        var str = config.getString(path);
        return ChatColor.translateAlternateColorCodes('&', str == null ? "" : str);
    }
}

