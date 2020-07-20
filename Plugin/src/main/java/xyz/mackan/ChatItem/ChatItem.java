// TODO: Potion names (Item types: POTION, LINGERING_POTION, SPLASH_POTION)
// TODO: Names of player heads (Item type: PLAYER_HEAD)

package xyz.mackan.ChatItem;

import me.pikamug.localelib.LocaleLib;
import me.pikamug.localelib.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.ServicePriority;
import xyz.mackan.ChatItem.API.ChatItemsAPI;
import xyz.mackan.ChatItem.API.ItemAPI;
import xyz.mackan.ChatItem.commands.ChatItemCommand;
import xyz.mackan.ChatItem.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class ChatItem extends JavaPlugin {
	private static LocaleManager localeManager;
	private static boolean isEssChatEnabled;

	private static PluginDescriptionFile descriptionFile;

	public static LocaleManager getLocaleManager () {
		return localeManager;
	}
	public static boolean getIsEssChatEnabled () { return isEssChatEnabled; }

	public static PluginDescriptionFile getDescriptionFile () { return descriptionFile; }

	public static ConfigHolder configHolder = new ConfigHolder();


	public void loadConfig () {
		FileConfiguration config = this.getConfig();

		configHolder.multiple = config.getBoolean("showQuantities.multiple", true);
		configHolder.singleItems = config.getBoolean("showQuantities.singleItems", false);
	}

	private void loadAPI () {
		ChatItemsAPI api = null;
		ItemAPI itemAPI = null;

		String packageName = ChatItem.class.getPackage().getName();
		String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);
		try {
			api = (ChatItemsAPI) Class.forName(packageName + "." + internalsName).newInstance();
			itemAPI = (ItemAPI) Class.forName(packageName + "." + internalsName).newInstance();

			Bukkit.getServicesManager().register(ChatItemsAPI.class, api, this, ServicePriority.Highest);
			Bukkit.getServicesManager().register(ItemAPI.class, itemAPI, this, ServicePriority.Highest);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException exception) {
			Bukkit.getLogger().log(Level.SEVERE, "ChatItems could not find a valid implementation for this server version.");
		}
	}

	@Override
	public void onEnable() {

		getLogger().info("[ChatItems] is enabled.");

		loadAPI();

		this.saveDefaultConfig();

		loadConfig();

		getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);

		LocaleLib localeLib = (LocaleLib) getServer().getPluginManager().getPlugin("LocaleLib");
		localeManager = localeLib.getLocaleManager();

		this.getCommand("ci").setExecutor(new ChatItemCommand());

		descriptionFile = this.getDescription();
	}

	@Override
	public void onDisable() {
		getLogger().info("[ChatItems] is disabled.");
	}
}
