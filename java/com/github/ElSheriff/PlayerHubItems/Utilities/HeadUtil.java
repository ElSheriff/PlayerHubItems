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

package com.github.ElSheriff.PlayerHubItems.Utilities;

import com.github.ElSheriff.PlayerHubItems.Manager.ConfigManager;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class HeadUtil {
    public static String head_type = ConfigManager.getHeadConfig().getString("HeadConfig.Type");

    public static void giveHeadWithItemFlag(Player player) {
        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta)stack.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName(ColorUtil.getColor(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName()))));

        ArrayList<String> itemLore = new ArrayList();
        for (String lore : ConfigManager.getHeadConfig().getStringList("HeadConfig.ItemMeta.DisplayLore")) {
            itemLore.add(ColorUtil.getColor(lore.replace("{player}", player.getName())));
            meta.setLore(itemLore);
        }

        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });

        stack.setItemMeta(meta);
        player.getInventory().setItem(ConfigManager.getHeadConfig().getInt("HeadConfig.Position") -1, stack);
    }

    public static void giveHeadWithoutItemFlag(Player player) {
        ItemStack stack = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta meta = (SkullMeta)stack.getItemMeta();
        meta.setOwner(player.getName());
        meta.setDisplayName(ColorUtil.getColor(ColorUtil.getColor(ConfigManager.getHeadConfig().getString("HeadConfig.ItemMeta.DisplayName").replace("{player}", player.getName()))));

        ArrayList<String> itemLore = new ArrayList();
        for (String lore : ConfigManager.getHeadConfig().getStringList("HeadConfig.ItemMeta.DisplayLore")) {
            itemLore.add(ColorUtil.getColor(lore.replace("{player}", player.getName())));
            meta.setLore(itemLore);
        }

        stack.setItemMeta(meta);
        player.getInventory().setItem(ConfigManager.getHeadConfig().getInt("HeadConfig.Position") -1, stack);
    }
}