// TODO: Make this work with essentialschat
// TODO: Make this replace all [item] tags
package xyz.mackan.ChatItem;

import me.pikamug.localelib.LocaleLib;
import me.pikamug.localelib.LocaleManager;
import xyz.mackan.ChatItem.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatItem extends JavaPlugin {
	private static LocaleManager localeManager;

	public static LocaleManager getLocaleManager () {
		return localeManager;
	}

	@Override
	public void onEnable() {
		getLogger().info("[ChatItem] is enabled.");
		getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);

		LocaleLib localeLib = (LocaleLib) getServer().getPluginManager().getPlugin("LocaleLib");
		localeManager = localeLib.getLocaleManager();
	}

	@Override
	public void onDisable() {
		getLogger().info("[ChatItem] is disabled.");
	}
}
