package com.github.laefye.pixelbattle.events;

import com.github.laefye.pixelbattle.Color;
import com.github.laefye.pixelbattle.Member;
import com.github.laefye.pixelbattle.PixelBattlePlugin;
import com.github.laefye.pixelbattle.menus.Palette;
import com.github.laefye.pixelbattle.SomeConstants;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerEvents implements Listener {
    private PixelBattlePlugin plugin;

    public PlayerEvents(PixelBattlePlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        var player = event.getPlayer();
        player.setAllowFlight(true);

        if (plugin.getMember(player) == null) {
            var member = new Member(plugin, player);
            member.load();
            plugin.getMembers().add(member);
        }
    }

    private boolean needToSkip(HumanEntity entity) {
        return plugin.getMember((Player) entity) == null;
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        if (needToSkip(event.getPlayer()))
            return;
        plugin.getMenuList().remove(PixelBattlePlugin.getInstance().getMenu(event.getInventory()));
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (needToSkip(event.getWhoClicked()))
            return;
        var menu = plugin.getMenu(event.getClickedInventory());
        if (menu != null) {
            if (menu.getInventory() == event.getClickedInventory()) {
                menu.click(event.getSlot(), (Player) event.getWhoClicked());
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (needToSkip(event.getPlayer()))
            return;
        var slot = event.getPlayer().getInventory().getHeldItemSlot();
        if (slot == SomeConstants.PALLETE_SLOT) {
            new Palette(plugin).show(event.getPlayer());
        }
        if (slot < 4 && event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            var member = plugin.getMember(event.getPlayer());
            var block = event.getClickedBlock();
            if (block != null) {
                member.place(block.getX(), block.getY(), block.getZ(), slot);
            }
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onHurt(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player)
        if (needToSkip(player))
            return;
        event.setCancelled(true);
    }
}
