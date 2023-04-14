package com.github.laefye.pixelbattle;

import com.github.laefye.pixelbattle.abstracts.Menu;
import com.github.laefye.pixelbattle.async.AsyncBuilder;
import com.github.laefye.pixelbattle.commands.PixelBattleCommand;
import com.github.laefye.pixelbattle.events.PlayerEvents;
import com.github.laefye.pixelbattle.modules.DynmapModuleAPI;
import com.github.laefye.pixelbattle.modules.PixelBattlePlaceholderExtension;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.dynmap.DynmapCommonAPI;
import org.dynmap.DynmapCommonAPIListener;

import java.io.File;
import java.util.ArrayList;

public final class PixelBattlePlugin extends JavaPlugin {
    private static PixelBattlePlugin instance;
    private Canvas canvas;
    private ArrayList<Menu> menuList = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private PixelBattlePlaceholderExtension pixelBattlePlaceholderExtension;
    private DynmapModuleAPI dynmapModuleAPI;
    private TopList topList = new TopList();
    private LangConfig langConfig = new LangConfig(this);

    @Override
    public void onEnable() {
        instance = this;
        prepare();

        canvas = new Canvas(this, 2048, 2048, getServer().getWorlds().get(0), -1024, -63, -1024);
        topList.load();

        getServer().getPluginManager().registerEvents(new PlayerEvents(this), this);
        if(Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            pixelBattlePlaceholderExtension = new PixelBattlePlaceholderExtension(this);
            pixelBattlePlaceholderExtension.register();
        }
        if(Bukkit.getPluginManager().getPlugin("dynmap") != null) {
            dynmapModuleAPI = new DynmapModuleAPI(this);
            DynmapCommonAPIListener.register(dynmapModuleAPI);
        }

        for (var player : getServer().getOnlinePlayers()) {
            if (getMember(player) == null) {
                var member = new Member(this, player);
                member.load();
                getMembers().add(member);
            }
        }

        getCommand("pixelbattle").setExecutor(new PixelBattleCommand(this));
    }

    private void prepare() {
        createFolders();
        langConfig.loadConfig();
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

    public void createFolders() {
        if (!new File(SomeConstants.PLUGIN_FOLDER).exists()) {
            new File(SomeConstants.PLUGIN_FOLDER).mkdir();
        }
        if (!new File(SomeConstants.MEMBERS_FOLDER).exists()) {
            new File(SomeConstants.MEMBERS_FOLDER).mkdir();
        }
    }

    public DynmapModuleAPI getDynmapModuleAPI() {
        return dynmapModuleAPI;
    }
}
