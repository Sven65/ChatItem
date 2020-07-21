package xyz.mackan.ChatItem.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.mackan.ChatItem.ChatItem;
import xyz.mackan.ChatItem.util.VersionUtil;

public class ChatItemCommand implements CommandExecutor {
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		String version = ChatItem.getDescriptionFile().getVersion();

		sender.sendMessage("[ChatItems] Version "+version);
		sender.sendMessage("Show amount for single items: "+ChatItem.configHolder.singleItems);
		sender.sendMessage("Show amount for multiple items: "+ChatItem.configHolder.multiple);
		sender.sendMessage("Server Version: "+ VersionUtil.getVersion());

		return true;
	}
}
