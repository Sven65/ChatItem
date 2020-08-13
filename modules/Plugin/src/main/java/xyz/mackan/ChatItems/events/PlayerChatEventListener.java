package xyz.mackan.ChatItems.events;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import xyz.mackan.ChatItems.ChatItems;
import xyz.mackan.ChatItems.util.ChatParser;
import xyz.mackan.ChatItems.util.ItemUtil;
import xyz.mackan.ChatItems.ChatItemsAPI;
import xyz.mackan.ChatItems.types.StringPosition;

import java.util.List;

public class PlayerChatEventListener implements Listener {
	ChatItemsAPI api = ChatItems.getApi();

	public boolean shouldExitLoop (ItemStack itemStack) {
		if (itemStack == null || api.isAir(itemStack)) {
			return true;
		}

		return false;
	}


	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat (AsyncPlayerChatEvent event) {
		if (!ChatItems.shouldHandleChat) return;

		String message = event.getMessage();
		Player player = event.getPlayer();

		String format = event.getFormat();

		ItemStack itemInHand = api.getItemInMainHand(player);
		ItemStack itemInOffHand = api.getItemInOffHand(player);

		ItemStack boots = api.getBoots(player);
		ItemStack helmet = api.getHelmet(player);
		ItemStack chestplate = api.getChestplate(player);
		ItemStack legs = api.getLegs(player);

		TextComponent chatComponent = new TextComponent(String.format(format, player.getDisplayName(), ""));

		List<StringPosition> itemPositions = ChatParser.getStringPositions(message);

		if (api.isAir(itemInHand)) {
			itemInHand = null;
		}


		for (int i = 0;i<itemPositions.size();i++) {
			StringPosition current = itemPositions.get(i);

			StringPosition next = null;

			ItemStack itemToCheck = null;

			switch (current.chatPattern.type) {
				case HAND: itemToCheck = itemInHand;
					break;
				case OFFHAND: itemToCheck = itemInOffHand;
					break;
				case HELMET: itemToCheck = helmet;
					break;
				case CHESTPLATE: itemToCheck = chestplate;
					break;
				case LEGS: itemToCheck = legs;
					break;
				case BOOTS: itemToCheck = boots;
					break;
			}

			if (i + 1 < itemPositions.size()) {
				next = itemPositions.get(i + 1);
			}

			String start = message.substring(current.start, current.end);

			if (i == 0) {
				start = message.substring(0, current.start);
			}

			String end = "";

			String defaultString = "";

			if (next != null) {
				end = message.substring(current.end, next.start);
			}

			if (i == itemPositions.size()-1) {
				end = message.substring(current.end);
			}

			start = start.replaceAll(current.chatPattern.pattern, "");
			end = end.replaceAll(current.chatPattern.pattern, "");

			if (shouldExitLoop(itemToCheck)) {
				defaultString = current.chatPattern.pattern.replace("\\", "");
			}

			chatComponent.addExtra(start);

			chatComponent.addExtra(ItemUtil.getItemComponent(itemToCheck, defaultString));

			chatComponent.addExtra(end);
		}

		if (itemPositions.size() > 0) {
			event.getRecipients().forEach((Player recipient) -> {
				recipient.spigot().sendMessage(chatComponent);
			});

			event.setCancelled(true);
		}
	}
}
