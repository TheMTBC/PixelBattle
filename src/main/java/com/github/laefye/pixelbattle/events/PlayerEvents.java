package com.github.laefye.pixelbattle.events;

import com.github.laefye.pixelbattle.*;
import com.github.laefye.pixelbattle.menus.Palette;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.EquipmentSlot;

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

    private Member getMember(HumanEntity entity) {
        return plugin.getMember((Player) entity);
    }

    @EventHandler
    public void onCloseInventory(InventoryCloseEvent event) {
        if (getMember(event.getPlayer()) == null)
            return;
        plugin.getMenuList().remove(PixelBattlePlugin.getInstance().getMenu(event.getInventory()));
    }

    @EventHandler
    public void onClickInventory(InventoryClickEvent event) {
        if (getMember(event.getWhoClicked()) == null)
            return;
        var menu = plugin.getMenu(event.getClickedInventory());
        if (menu != null) {
            if (menu.getInventory() == event.getClickedInventory()) {
                menu.click(event.getSlot(), (Player) event.getWhoClicked());
            }
        }
        event.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent event) {
        var member = getMember(event.getPlayer());
        if (member == null || event.getHand() == EquipmentSlot.OFF_HAND)
            return;
        var slot = event.getPlayer().getInventory().getHeldItemSlot();
        if (slot == SomeConstants.PALLETE_SLOT && plugin.getCanvas().getMode() == Canvas.Mode.Build) {
            new Palette(plugin, color -> member.getBuild().addMaterial(color)).show(event.getPlayer());
        } else if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getPlayer().isSneaking()) {
            var color = event.getClickedBlock();
            if (color != null) {
                member.getBuild().addMaterial(color.getType());
            }
        } else if (slot < SomeConstants.PALLETE_SLOT && event.getAction() == Action.RIGHT_CLICK_BLOCK && plugin.getCanvas().getMode() == Canvas.Mode.Build) {
            var block = event.getClickedBlock();
            if (block != null) {
                member.use(member.getBuild(), block.getX(), block.getY(), block.getZ(), slot);
            }
        } else if (slot == SomeConstants.BOMB_SLOT && event.getAction() == Action.RIGHT_CLICK_BLOCK && plugin.getCanvas().getMode() == Canvas.Mode.Build) {
            var block = event.getClickedBlock();
            if (block != null) {
                member.use(member.getBomb(), block.getX(), block.getY(), block.getZ(), slot);
            }
        } else if (slot == SomeConstants.BOOSTER_SLOT && plugin.getCanvas().getMode() == Canvas.Mode.Build) {
            member.use(member.getBooster(), 0, 0, 0, slot);
        }
        event.setCancelled(true);
    }

    @EventHandler
    public void onHurt(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player player)
            if (getMember(player) == null)
                return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if (getMember(event.getPlayer()) == null)
            return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }
}
