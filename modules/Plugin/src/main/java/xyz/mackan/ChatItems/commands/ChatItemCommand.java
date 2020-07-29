package xyz.mackan.ChatItems.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.mackan.ChatItems.ChatItems;
import xyz.mackan.ChatItems.util.VersionUtil;

public class ChatItemCommand implements CommandExecutor {
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		String version = ChatItems.getDescriptionFile().getVersion();

		sender.sendMessage("[ChatItems] Version "+version);
		sender.sendMessage("Show amount for single items: "+ ChatItems.configHolder.singleItems);
		sender.sendMessage("Show amount for multiple items: "+ ChatItems.configHolder.multiple);
		sender.sendMessage("Server Version: "+ VersionUtil.getVersion());

		return true;
	}
}
