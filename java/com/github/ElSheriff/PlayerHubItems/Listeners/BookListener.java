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
import com.github.ElSheriff.PlayerHubItems.Utilities.BookUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.ColorUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.EffectUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.SoundUtil;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;

public class BookListener implements Listener {
    private Main plugin;
  
    public BookListener(Main instance) {
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
            if (player.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())))) {
                EffectUtil.sendEffect(player, ConfigManager.getBookConfig().getStringList("BookConfig.Effects"));
                SoundUtil.sendSound(player, ConfigManager.getBookConfig().getStringList("BookConfig.Sounds"));
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = (Player)event.getPlayer();
        if (ConfigManager.getBookConfig().getBoolean("BookConfig.GiveBook.OnPlayerJoin", true)) {
            if (player.hasPermission("phi.book.join")) {
                if (BookUtil.book_type.equalsIgnoreCase("WithItemFlag"))  {
                    BookUtil.giveBookWithItemFlag(player);
                } else if (BookUtil.book_type.equalsIgnoreCase("WithoutItemFlag"))  {
                    BookUtil.giveBookWithoutItemFlag(player);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = (Player)event.getPlayer();
        if (ConfigManager.getBookConfig().getBoolean("BookConfig.GiveBook.OnPlayerRespawn", true)) {
            if (player.hasPermission("phi.book.respawn")) {
                if (BookUtil.book_type.equalsIgnoreCase("WithItemFlag"))  {
                    BookUtil.giveBookWithItemFlag(player);
                } else if (BookUtil.book_type.equalsIgnoreCase("WithoutItemFlag"))  {
                    BookUtil.giveBookWithoutItemFlag(player);
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
        if (ConfigManager.getBookConfig().getBoolean("BookConfig.Disable.DropBook", true)) {
            if (event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())))) {
                if (!player.hasPermission("phi.book.bypass")) {
                    event.setCancelled(true);
                } else {
                    event.setCancelled(false);
                }
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
        if (ConfigManager.getBookConfig().getBoolean("BookConfig.Disable.InteractBook", true)) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())))) {
                if (!player.hasPermission("phi.book.bypass")) {
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
        for (ItemStack book : stack) {
            if (book.getItemMeta() == null) {
                return;
            }
            if (book.getItemMeta().getDisplayName() == null) {
                return;
            }
            if (ConfigManager.getBookConfig().getBoolean("BookConfig.Disable.DropBook", true)) {
                if (book.getItemMeta().getDisplayName().equalsIgnoreCase(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())))) {
                    event.getDrops().remove(book);
                }
            }
        }
    }
}