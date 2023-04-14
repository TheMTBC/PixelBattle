package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.abstracts.Menu;
import com.github.laefye.pixelbattle.async.AsyncBuilder;
import com.github.laefye.pixelbattle.commands.PixelBattleCommand;
import com.github.laefye.pixelbattle.events.PlayerEvents;
import com.github.laefye.pixelbattle.modules.PixelBattlePlaceholderExtension;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class PixelBattlePlugin extends JavaPlugin {
    private static PixelBattlePlugin instance;
    private Canvas canvas;
    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private PixelBattlePlaceholderExtension pixelBattlePlaceholderExtension;
    private TopList topList = new TopList();
    private LangConfig langConfig = new LangConfig(this);

    @Override
    public void onEnable() {
        instance = this;
        canvas = new Canvas(this, 256, 256, getServer().getWorld("world"), 0, 319, 0);
        topList.load();

        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            pixelBattlePlaceholderExtension = new PixelBattlePlaceholderExtension(this);
            pixelBattlePlaceholderExtension.register();
        }
        getCommand("pixelbattle").setExecutor(new PixelBattleCommand(this));
    }

    public static PixelBattlePlugin getInstance() {
        return instance;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    @Override
    public void onDisable() {
        if (pixelBattlePlaceholderExtension != null) {
            pixelBattlePlaceholderExtension.unregister();
        }
        for (var member : members) {
            member.save();
        }
        topList.save();
    }

    public ArrayList<Menu> getMenuList() {
        return menuList;
    }

    public Menu getMenu(Inventory inventory) {
        for (var menu : menuList) {
            if (menu.getInventory() == inventory) {
                return menu;
            }
        }
        return null;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public Member getMember(Player player) {
        var uuid = player.getUniqueId();
        for (var member : members) {
            if (uuid.equals(member.getId())) {
                return member;
            }
        }
        return null;
    }

    public TopList getTopList() {
        return topList;
    }

    public LangConfig getLangConfig() {
        return langConfig;
    }
}
