package xyz.mackan.ChatItem.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;

public class VersionUtil {
	public static String getVersion() {
		final String packageName = Bukkit.getServer().getClass().getPackage().getName();

		return packageName.substring(packageName.lastIndexOf('.') + 1);
	}
}
