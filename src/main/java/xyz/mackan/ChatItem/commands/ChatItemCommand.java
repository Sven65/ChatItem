package xyz.mackan.ChatItem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import xyz.mackan.ChatItem.ChatItem;

public class ChatItemCommand implements CommandExecutor {

	private String chatPrefix = ChatColor.DARK_GRAY + "[" + ChatColor.GREEN + "ChatItem" + ChatColor.DARK_GRAY + "] ";

	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		String version = ChatItem.getDescriptionFile().getVersion();
		boolean isEssChatEnabled = ChatItem.getIsEssChatEnabled();
		boolean isVaultEnabled = ChatItem.getIsVaultEnabled();
		boolean singleItemsBool = ChatItem.configHolder.singleItems;
		boolean multipleBool = ChatItem.configHolder.multiple;

		String esschat;
		String vault;
		String singleItems;
		String multiple;

		if (isEssChatEnabled) {
			esschat = ChatColor.GREEN + "" + isEssChatEnabled;
		} else {
			esschat = ChatColor.RED + "" + isEssChatEnabled;
		}

		if (isVaultEnabled) {
			vault = ChatColor.GREEN + "" + isVaultEnabled;
		} else {
			vault = ChatColor.RED + "" + isVaultEnabled;
		}

		if (singleItemsBool) {
			singleItems = ChatColor.GREEN + "" + singleItemsBool;
		} else {
			singleItems = ChatColor.RED + "" + singleItemsBool;
		}

		if (multipleBool) {
			multiple = ChatColor.GREEN + "" + multipleBool;
		} else {
			multiple = ChatColor.RED + "" + multipleBool;
		}

		sender.sendMessage(chatPrefix + ChatColor.YELLOW + "ChatItem v" + ChatColor.GREEN + version);
		sender.sendMessage(chatPrefix + ChatColor.GRAY + "EssentialsChat Enabled: " + esschat);
		sender.sendMessage(chatPrefix + ChatColor.GRAY + "Vault Enabled: " + vault);
		sender.sendMessage(chatPrefix + ChatColor.GRAY + "Show Amount for Single Items: " + singleItems);
		sender.sendMessage(chatPrefix + ChatColor.GRAY + "Show Amount for Multiple Items: " + multiple);

		return true;
	}
}
