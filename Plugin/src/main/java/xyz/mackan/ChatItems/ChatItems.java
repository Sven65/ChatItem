package xyz.mackan.ChatItems;

import me.pikamug.localelib.LocaleLib;
import me.pikamug.localelib.LocaleManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginDescriptionFile;
import xyz.mackan.ChatItems.commands.ChatItemCommand;
import xyz.mackan.ChatItems.events.PlayerChatEventListener;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.mackan.ChatItems.util.ConfigHolder;
import xyz.mackan.ChatItems.util.PatternManager;
import xyz.mackan.chatitems.ChatItemsAPI;
import xyz.mackan.chatitems.types.ChatPattern;

import java.util.logging.Level;

/**
 * Base plugin
 */
public class ChatItems extends JavaPlugin {
	private static LocaleManager localeManager;

	private static PluginDescriptionFile descriptionFile;

	public static LocaleManager getLocaleManager () {
		return localeManager;
	}

	public static PluginDescriptionFile getDescriptionFile () { return descriptionFile; }

	public static ConfigHolder configHolder = new ConfigHolder();

	private static ChatItemsAPI api;

	/**
	 * If the ChatItems plugin should handle chat
	 */
	public static boolean shouldHandleChat = true;

	/**
	 * Sets if ChatItems should handle chat
	 * @param handleChat boolean If ChatItems should handle chat
	 */
	public static void setHandleChat (boolean handleChat) {
		shouldHandleChat = handleChat;
	}

	/**
	 * Loads the configuration for ChatItems from file
	 */
	private void loadConfig () {
		FileConfiguration config = this.getConfig();

		configHolder.multiple = config.getBoolean("showQuantities.multiple", true);
		configHolder.singleItems = config.getBoolean("showQuantities.singleItems", false);
	}

	/**
	 * Loads ChatItems version support APIs
	 */
	private void loadAPI () {
		String packageName = ChatItems.class.getPackage().getName();
		String internalsName = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3].substring(1);

		try {
			api = (ChatItemsAPI) Class.forName(packageName + ".API.ChatItemsAPI_v" + internalsName).newInstance();
			//Bukkit.getServicesManager().register(ChatItemsAPI.class, api, this, ServicePriority.Highest);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | ClassCastException exception) {
			Bukkit.getLogger().log(Level.SEVERE, "ChatItems could not find a valid implementation for this server version.");
		}
	}

	/**
	 * Registers the default patterns for ChatItems
	 */
	private void registerDefaultPatterns () {
		PatternManager.registerPattern(ChatPattern.Type.HAND, "\\[item\\]");
		PatternManager.registerPattern(ChatPattern.Type.HAND, "\\[i\\]");
		PatternManager.registerPattern(ChatPattern.Type.HAND, "\\[hand\\]");

		PatternManager.registerPattern(ChatPattern.Type.OFFHAND, "\\[offhand\\]");

		PatternManager.registerPattern(ChatPattern.Type.HELMET, "\\[helmet\\]");
		PatternManager.registerPattern(ChatPattern.Type.HELMET, "\\[helm\\]");
		PatternManager.registerPattern(ChatPattern.Type.HELMET, "\\[head\\]");


		PatternManager.registerPattern(ChatPattern.Type.CHESTPLATE, "\\[chestplate\\]");
		PatternManager.registerPattern(ChatPattern.Type.CHESTPLATE, "\\[chest\\]");


		PatternManager.registerPattern(ChatPattern.Type.LEGS, "\\[legs\\]");

		PatternManager.registerPattern(ChatPattern.Type.BOOTS, "\\[boots\\]");
	}

	@Override
	public void onEnable() {

		getLogger().info("[ChatItems] enabled.");

		loadAPI();

		this.saveDefaultConfig();

		loadConfig();

		registerDefaultPatterns();

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

	/**
	 * Gets the registered ChatItemsAPI
	 * @return ChatItemsAPI
	 */
	public static ChatItemsAPI getApi () {
		return api;
	}
}
