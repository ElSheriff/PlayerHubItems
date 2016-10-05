/*
 *
 *     Copyright (C) 2016 ElSheriff
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.ElSheriff.PlayerHubItems.Listeners;

import com.github.ElSheriff.PlayerHubItems.Main;
import com.github.ElSheriff.PlayerHubItems.Manager.ConfigManager;
import com.github.ElSheriff.PlayerHubItems.Utilities.ColorUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.EffectUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.HeadUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.SoundUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class HeadListener implements Listener {
    private Main plugin;
  
    public HeadListener(Main instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = (Player)event.getPlayer();
        if (player.getItemInHand() == null) {
            return;
        }
        if (player.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (player.getItemInHand().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (event.getAction() != Action.RIGHT_CLICK_AIR || event.getAction() != Action.RIGHT_CLICK_BLOCK) {
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName())))) {
                if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.HeadCommand.ByConsole", true)) {
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), ConfigManager.getHeadConfig().getString("HeadConfig.HeadCommand.Command").replace("{player}", player.getName()));
                } else {
                    Bukkit.dispatchCommand(player, ConfigManager.getHeadConfig().getString("HeadConfig.HeadCommand.Command").replace("{player}", player.getName()));
                }
                EffectUtil.sendEffect(player, ConfigManager.getHeadConfig().getStringList("HeadConfig.Effects"));
                SoundUtil.sendSound(player, ConfigManager.getHeadConfig().getStringList("HeadConfig.Sounds"));
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player)event.getWhoClicked();
        if (event.getClick() == null) {
            return;
        }
        if (event.getClick() == ClickType.UNKNOWN) {
            return;
        }
        if (event.getCurrentItem() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta() == null) {
            return;
        }
        if (event.getCurrentItem().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.Disable.InteractHead", true)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName())))) {
                if (!player.hasPermission("phi.head.bypass")) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = (Player)event.getPlayer();
        if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.GiveHead.OnPlayerJoin", true)) {
            if (player.hasPermission("phi.head.join")) {
                if (HeadUtil.head_type.equalsIgnoreCase("WithItemFlag"))  {
                    HeadUtil.giveHeadWithItemFlag(player);
                } else if (HeadUtil.head_type.equalsIgnoreCase("WithoutItemFlag"))  {
                    HeadUtil.giveHeadWithoutItemFlag(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = (Player)event.getPlayer();
        if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.GiveHead.OnPlayerRespawn", true)) {
            if (player.hasPermission("phi.head.respawn")) {
                if (HeadUtil.head_type.equalsIgnoreCase("WithItemFlag"))  {
                    HeadUtil.giveHeadWithItemFlag(player);
                } else if (HeadUtil.head_type.equalsIgnoreCase("WithoutItemFlag"))  {
                    HeadUtil.giveHeadWithoutItemFlag(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = (Player)event.getPlayer();
        if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.Disable.DropHead", true)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName())))) {
                if (!player.hasPermission("phi.head.bypass")) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = (Player)event.getEntity();
        List<ItemStack> stack = new ArrayList(event.getDrops());
        for (ItemStack head : stack) {
            if (head.getItemMeta() == null) {
                return;
            }
            if (head.getItemMeta().getDisplayName() == null) {
                return;
            }
            if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.Disable.DropHead", true)) {
                if (head.getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName())))) {
                    event.getDrops().remove(head);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        if (event.getItemInHand() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta() == null) {
            return;
        }
        if (event.getItemInHand().getItemMeta().getDisplayName() == null) {
            return;
        }
        if (ConfigManager.getHeadConfig().getBoolean("HeadConfig.Disable.PlaceHead", true)) {
            if (event.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName())))) {
                if (!player.hasPermission("phi.head.bypass")) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
            }
        }
    }
}