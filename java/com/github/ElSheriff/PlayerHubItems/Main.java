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

package com.github.ElSheriff.PlayerHubItems;

import com.github.ElSheriff.PlayerHubItems.Listeners.BookListener;
import com.github.ElSheriff.PlayerHubItems.Listeners.HeadListener;
import com.github.ElSheriff.PlayerHubItems.Manager.ConfigManager;
import com.github.ElSheriff.PlayerHubItems.Utilities.BookUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.ColorUtil;
import com.github.ElSheriff.PlayerHubItems.Utilities.HeadUtil;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public static Plugin getPlugin() {
        return Bukkit.getServer().getPluginManager().getPlugin("PlayerHubItems");
    }

    @Override
    public void onEnable() {
        instance = this;
        ConfigManager.createConfig();

        getServer().getPluginManager().registerEvents(this, this);
        getServer().getPluginManager().registerEvents(new BookListener(this), this);
        getServer().getPluginManager().registerEvents(new HeadListener(this), this);

        Bukkit.getServer().getLogger().info("============================");
        Bukkit.getServer().getLogger().info("=====  PlayerHubItems  =====");
        Bukkit.getServer().getLogger().info("====  STATUS - ENABLED  ====");
        Bukkit.getServer().getLogger().info("============================");
    }

    @Override
    public void onDisable() {
        Bukkit.getServer().getLogger().info("============================");
        Bukkit.getServer().getLogger().info("=====  PlayerHubItems  =====");
        Bukkit.getServer().getLogger().info("====  STATUS - DISABLE  ====");
        Bukkit.getServer().getLogger().info("============================");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String string, String[] arguments) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ColorUtil.getColorConsole("&cError &8| &fYou must be a player to execute this command."));
            return true;
        }
        Player player = (Player)sender;
        if (command.getName().equalsIgnoreCase("playerhubitems")) {
            if (arguments.length == 0) {
                    player.sendMessage(ColorUtil.getColor("&2&m====================================================="));
                    player.sendMessage(ColorUtil.getColor("&f "));
                    player.sendMessage(ColorUtil.getColor(" &9PlayerHubItems &6by &dElSheriff"));
                    player.sendMessage(ColorUtil.getColor("&f "));
                    player.sendMessage(ColorUtil.getColor(" &bCommands:"));
                    player.sendMessage(ColorUtil.getColor(" &e/playerhubitems &7- &aDisplay this help text."));
                    player.sendMessage(ColorUtil.getColor(" &e/playerhubitems reload &7- &aReload all files of the plugin."));
                    player.sendMessage(ColorUtil.getColor(" &e/playerhubitems head &7- &aFor use the head commands."));
                    player.sendMessage(ColorUtil.getColor(" &e/playerhubitems book &7- &aFor use the book commands."));
                    player.sendMessage(ColorUtil.getColor("&f "));
                    player.sendMessage(ColorUtil.getColor("&2&m====================================================="));
            } else if (arguments.length == 1) {
                if ((arguments[0].equalsIgnoreCase("head") && (player.hasPermission("phi.command.head")))) {
                    sender.sendMessage(ColorUtil.getColor("&a&l[?] &6Use &d/phi head get &6to get your head."));
                } else if ((arguments[0].equalsIgnoreCase("book") && (player.hasPermission("phi.command.book")))) {
                    sender.sendMessage(ColorUtil.getColor("&a&l[?] &6Use &d/phi book get/save &6to get or save the book."));
                } else if ((arguments[0].equalsIgnoreCase("reload") && (player.hasPermission("phi.command.reload")))) {
                    ConfigManager.reloadBookConfig();
                    ConfigManager.reloadHeadConfig();
                    ConfigManager.reloadMessagesConfig();
                    player.sendMessage(ConfigManager.getMessagesConfig().getString("MessagesConfig.ReloadConfig").replace("&", "§"));
                }
            } else if (arguments.length == 2) {
                if ((arguments[0].equalsIgnoreCase("head") && (player.hasPermission("phi.command.head")))) {
                    if (arguments[1].equalsIgnoreCase("get")) {
                        if (HeadUtil.head_type.equalsIgnoreCase("WithItemFlag"))  {
                            HeadUtil.giveHeadWithItemFlag(player);
                        } else if (HeadUtil.head_type.equalsIgnoreCase("WithoutItemFlag"))  {
                            HeadUtil.giveHeadWithoutItemFlag(player);
                        }
                        player.sendMessage(ConfigManager.getMessagesConfig().getString("MessagesConfig.GetHead").replace("&", "§"));
                    }
                } else if ((arguments[0].equalsIgnoreCase("book") && (player.hasPermission("phi.command.book")))) {
                    if (arguments[1].equalsIgnoreCase("get")) {
                        if (BookUtil.book_type.equalsIgnoreCase("WithItemFlag"))  {
                            BookUtil.giveBookWithItemFlag(player);
                        } else if (BookUtil.book_type.equalsIgnoreCase("WithoutItemFlag"))  {
                            BookUtil.giveBookWithoutItemFlag(player);
                        }
                        player.sendMessage(ConfigManager.getMessagesConfig().getString("MessagesConfig.GetBook").replace("&", "§"));
                    } else if (arguments[1].equalsIgnoreCase("save")) {
                        BookUtil.saveBook(player);
                    }
                }
            }
        }
        return false;
    }
}