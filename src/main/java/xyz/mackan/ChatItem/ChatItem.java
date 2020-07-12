// TODO: Potion names (Item types: POTION, LINGERING_POTION, SPLASH_POTION)
// TODO: Names of player heads (Item type: PLAYER_HEAD)

package xyz.mackan.ChatItem;

import me.pikamug.localelib.LocaleLib;
import me.pikamug.localelib.LocaleManager;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.yaml.snakeyaml.Yaml;
import xyz.mackan.ChatItem.commands.ChatItemCommand;
import xyz.mackan.ChatItem.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;

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

	@Override
	public void onEnable() {
		getLogger().info("[ChatItem] is enabled.");

		this.saveDefaultConfig();

		loadConfig();

		getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);

		LocaleLib localeLib = (LocaleLib) getServer().getPluginManager().getPlugin("LocaleLib");
		localeManager = localeLib.getLocaleManager();

		isEssChatEnabled = getServer().getPluginManager().getPlugin("EssentialsChat") != null;

		getLogger().info("[ChatItem] Is EssentialsChat enabled: "+isEssChatEnabled);

		this.getCommand("ci").setExecutor(new ChatItemCommand());

		descriptionFile = this.getDescription();
	}

	@Override
	public void onDisable() {
		getLogger().info("[ChatItem] is disabled.");
	}
}
