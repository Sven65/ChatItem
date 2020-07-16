// TODO: Potion names (Item types: POTION, LINGERING_POTION, SPLASH_POTION)
// TODO: Names of player heads (Item type: PLAYER_HEAD)

package xyz.mackan.ChatItem;

import me.pikamug.localelib.LocaleLib;
import me.pikamug.localelib.LocaleManager;
import net.milkbowl.vault.chat.Chat;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;

import xyz.mackan.ChatItem.commands.ChatItemCommand;
import xyz.mackan.ChatItem.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;

public class ChatItem extends JavaPlugin {
	private static LocaleManager localeManager;
	private static boolean isEssChatEnabled;
	private static boolean isVaultEnabled;

	private static PluginDescriptionFile descriptionFile;
	
	private static Chat chat = null;

	public static LocaleManager getLocaleManager () { return localeManager; }
	public static boolean getIsEssChatEnabled () { return isEssChatEnabled; }
	public static boolean getIsVaultEnabled () { return isVaultEnabled; }
	public static PluginDescriptionFile getDescriptionFile () { return descriptionFile; }
	public static Chat getChat() { return chat; }
	
	private boolean setupChat() {
		RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
		chat = rsp.getProvider();
		
		return chat != null;
	}

	public static ConfigHolder configHolder = new ConfigHolder();

	public void loadConfig () {
		FileConfiguration config = this.getConfig();

		configHolder.multiple = config.getBoolean("showQuantities.multiple", true);
		configHolder.singleItems = config.getBoolean("showQuantities.singleItems", false);
	}

	@Override
	public void onEnable() {
		getLogger().info("ChatItem has been enabled.");

		this.saveDefaultConfig();

		loadConfig();

		getServer().getPluginManager().registerEvents(new PlayerChatEventListener(), this);

		LocaleLib localeLib = (LocaleLib) getServer().getPluginManager().getPlugin("LocaleLib");
		localeManager = localeLib.getLocaleManager();

		isEssChatEnabled = getServer().getPluginManager().getPlugin("EssentialsChat") != null;
		isVaultEnabled = getServer().getPluginManager().getPlugin("Vault") != null;

		if (isVaultEnabled) {
			
			setupChat();
			
		}
		
		getLogger().info("EssentialsChat Enabled: "+isEssChatEnabled);
		getLogger().info("Vault Enabled: "+isVaultEnabled);

		this.getCommand("ci").setExecutor(new ChatItemCommand());

		descriptionFile = this.getDescription();
	}

	@Override
	public void onDisable() {
		getLogger().info("ChatItem has been disabled.");
	}
}
