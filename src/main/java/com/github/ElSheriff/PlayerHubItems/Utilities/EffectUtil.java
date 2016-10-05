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

import java.util.List;
import org.bukkit.Effect;
import org.bukkit.entity.Player;

public class EffectUtil {
    
    public static void sendEffect(Player player, List<String> effects) {
        try {
            for (int i = 0; i < effects.size(); ++i) {
                if (effects.get(i).equalsIgnoreCase("")) continue;
                    player.getLocation().getWorld().playEffect(player.getLocation(), getEffect(effects.get(i)), 1);
                }
            } catch (IllegalArgumentException exception) {
                System.out.println(exception);
        }
    }

    public static Effect getEffect(String effect) {
        return Effect.valueOf(effect.toUpperCase());
    }
}