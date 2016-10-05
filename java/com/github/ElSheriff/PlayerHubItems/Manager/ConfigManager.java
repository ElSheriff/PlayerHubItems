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

package com.github.ElSheriff.PlayerHubItems.Manager;

import com.github.ElSheriff.PlayerHubItems.Main;
import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class ConfigManager {
    private static File bookFile;
    private static FileConfiguration bookConfig;
    private static File headFile;
    private static FileConfiguration headConfig;
    private static File messagesFile;
    private static FileConfiguration messagesConfig;
  
    public static void createConfig() {
        bookFile = new File(Main.getPlugin().getDataFolder(), "book.yml");
        if (!bookFile.exists()) {
            Main.getPlugin().saveResource("book.yml", true);
        }
        bookConfig = YamlConfiguration.loadConfiguration(bookFile);

        headFile = new File(Main.getPlugin().getDataFolder(), "head.yml");
        if (!headFile.exists()) {
            Main.getPlugin().saveResource("head.yml", true);
        }
        headConfig = YamlConfiguration.loadConfiguration(headFile);

        messagesFile = new File(Main.getPlugin().getDataFolder(), "messages.yml");
        if (!messagesFile.exists()) {
            Main.getPlugin().saveResource("messages.yml", true);
        }
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
  
    public static FileConfiguration getBookConfig() {
        return bookConfig;
    }
  
    public static void reloadBookConfig() {
        bookConfig = YamlConfiguration.loadConfiguration(bookFile);
    }
  
    public static void saveBookConfig() {
        try {
            bookConfig.save(bookFile);
        } catch (IOException exception) {
            System.out.print(exception);
        }
    }

    public static FileConfiguration getHeadConfig() {
        return headConfig;
    }
  
    public static void reloadHeadConfig() {
        headConfig = YamlConfiguration.loadConfiguration(headFile);
    }
  
    public static void saveHeadConfig() {
        try {
            headConfig.save(headFile);
        } catch (IOException exception) {
            System.out.print(exception);
        }
    }

    public static FileConfiguration getMessagesConfig() {
        return messagesConfig;
    }
  
    public static void reloadMessagesConfig() {
        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
    }
  
    public static void saveMessagesConfig() {
        try {
            messagesConfig.save(messagesFile);
        } catch (IOException exception) {
            System.out.print(exception);
        }
    }
}