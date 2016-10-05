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
import org.bukkit.inventory.meta.BookMeta;

public class BookUtil {
    public static String book_type = ConfigManager.getBookConfig().getString("BookConfig.Type");

    public static void giveBookWithItemFlag(Player player) {
        ItemStack stack = (ItemStack)ConfigManager.getBookConfig().getItemStack("BookData");
        BookMeta meta = (BookMeta)stack.getItemMeta();
        
        for (int i = 1; i <= meta.getPageCount(); i++) {
            String page = (String)meta.getPage(i);
            page = ColorUtil.getColor(page.replace("{player}", player.getName()));
            meta.setPage(i, page);
        }

        stack.setAmount(1);

        ArrayList<String> itemLore = new ArrayList();
        for (String lore : ConfigManager.getBookConfig().getStringList("BookConfig.BookMeta.DisplayLore")) {
            itemLore.add(ColorUtil.getColor(lore.replace("{player}", player.getName())));
            meta.setLore(itemLore);
        }

        meta.setDisplayName(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())));
        meta.setAuthor(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayAuthor").replace("{player}", player.getName())));
        meta.setTitle(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())));

        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ENCHANTS });
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_ATTRIBUTES });
        meta.addItemFlags(new ItemFlag[] { ItemFlag.HIDE_POTION_EFFECTS });

        stack.setItemMeta(meta);
        player.getInventory().setItem(ConfigManager.getBookConfig().getInt("BookConfig.Position") -1, stack);
    }

    public static void giveBookWithoutItemFlag(Player player) {
        ItemStack stack = (ItemStack)ConfigManager.getBookConfig().getItemStack("BookData");
        BookMeta meta = (BookMeta)stack.getItemMeta();
        
        for (int i = 1; i <= meta.getPageCount(); i++) {
            String page = (String)meta.getPage(i);
            page = ColorUtil.getColor(page.replace("{player}", player.getName()).replace("{new_line}", "\n"));
            meta.setPage(i, page);
        }

        stack.setAmount(1);

        ArrayList<String> itemLore = new ArrayList();
        for (String lore : ConfigManager.getBookConfig().getStringList("BookConfig.BookMeta.DisplayLore")) {
            itemLore.add(ColorUtil.getColor(lore.replace("{player}", player.getName())));
            meta.setLore(itemLore);
        }

        meta.setDisplayName(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())));
        meta.setAuthor(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayAuthor").replace("{player}", player.getName())));
        meta.setTitle(ColorUtil.getColor(ConfigManager.getBookConfig().getString("BookConfig.BookMeta.DisplayTitle").replace("{player}", player.getName())));

        stack.setItemMeta(meta);
        player.getInventory().setItem(ConfigManager.getBookConfig().getInt("BookConfig.Position") -1, stack);
    }

    public static void saveBook(Player player) {
        if ((player.getItemInHand() != null) && (player.getItemInHand().getType() == Material.WRITTEN_BOOK)) {
            ConfigManager.getBookConfig().set("BookData", player.getItemInHand());
            player.sendMessage(ConfigManager.getMessagesConfig().getString("MessagesConfig.SaveBook").replace("&", "§"));
            ConfigManager.saveBookConfig();
        } else {
            player.sendMessage(ConfigManager.getMessagesConfig().getString("MessagesConfig.SaveBookError").replace("&", "§"));
        }
  } 
}