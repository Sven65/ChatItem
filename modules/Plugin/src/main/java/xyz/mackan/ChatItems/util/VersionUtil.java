package xyz.mackan.ChatItems.util;

import org.bukkit.Bukkit;

public class VersionUtil {
	/**
	 * Gets the bukkit version as a string
	 * @return String the version of bukkit
	 */
	public static String getVersion() {
		final String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

		return internalsName;
	}
}
