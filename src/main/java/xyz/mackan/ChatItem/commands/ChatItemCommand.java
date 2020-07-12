package xyz.mackan.ChatItem.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import xyz.mackan.ChatItem.ChatItem;

public class ChatItemCommand implements CommandExecutor {
	@Override
	public boolean onCommand (CommandSender sender, Command command, String label, String[] args) {
		String version = ChatItem.getDescriptionFile().getVersion();
		boolean isEssChatEnabled = ChatItem.getIsEssChatEnabled();

		sender.sendMessage("[ChatItems] Version "+version+"\nIs ess chat enabled: "+isEssChatEnabled);
		sender.sendMessage("Show amount for single items: "+ChatItem.configHolder.singleItems);
		sender.sendMessage("Show amount for multiple items: "+ChatItem.configHolder.multiple);

		return true;
	}
}
