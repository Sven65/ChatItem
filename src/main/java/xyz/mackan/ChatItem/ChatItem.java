// TODO: Make this work with essentialschat
// Todo: Make this work with non-named items
// TODO: Make this replace all [item] tags
package xyz.mackan.ChatItem;

import xyz.mackan.ChatItem.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatItem extends JavaPlugin {
	@Override
	public void onEnable() {
		getLogger().info("[xyz.mackan.ChatItem.ChatItem] is enabled.");
		getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);
	}

	@Override
	public void onDisable() {
		getLogger().info("[xyz.mackan.ChatItem.ChatItem] is disabled.");
	}
}
