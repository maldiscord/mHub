package lol.maltest.mhub.util;

import org.bukkit.ChatColor;

public class ChatUtil {
    public static String clr(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
