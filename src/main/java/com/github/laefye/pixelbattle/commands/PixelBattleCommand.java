package com.github.laefye.pixelbattle.commands;

import com.github.laefye.pixelbattle.Canvas;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class PixelBattleCommand implements CommandExecutor {
    private PixelBattlePlugin plugin;

    public PixelBattleCommand(PixelBattlePlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("fill")) {
                plugin.getCanvas().fill();
                sender.sendMessage("Started filling...");
            }
            if (args[0].equalsIgnoreCase("view")) {
                plugin.getCanvas().setMode(Canvas.Mode.View);
            }
            if (args[0].equalsIgnoreCase("build")) {
                plugin.getCanvas().setMode(Canvas.Mode.Build);
            }
        }
        if (args.length == 3) {
            if (args[0].equalsIgnoreCase("bomb")) {
                var player = plugin.getServer().getPlayer(args[1]);
                var member = plugin.getMember(player);
                if (member == null) {
                    sender.sendMessage("not found user");
                    return false;
                }
                var count = Integer.parseInt(args[2]);
                var bomb = member.getBomb();
                bomb.setAmount(bomb.getAmount()+count);
                bomb.updateInventory();
            } else if (args[0].equalsIgnoreCase("booster")) {
                var player = plugin.getServer().getPlayer(args[1]);
                var member = plugin.getMember(player);
                if (member == null) {
                    sender.sendMessage("not found user");
                    return false;
                }
                var count = Integer.parseInt(args[2]);
                var booster = member.getBooster();
                booster.setAmount(booster.getAmount()+count);
                booster.updateInventory();
            }
        }
        return true;
    }
}
