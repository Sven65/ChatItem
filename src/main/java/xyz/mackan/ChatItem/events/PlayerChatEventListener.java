package xyz.mackan.ChatItem.events;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.mackan.ChatItem.PatternType;
import xyz.mackan.ChatItem.StringPosition;
import xyz.mackan.ChatItem.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatEventListener implements Listener {
	public List<StringPosition> getStringPositions (String message) {
		List<StringPosition> stringPositions = new ArrayList<StringPosition>();
		for (PatternType type : PatternType.values()) {
			Pattern r = Pattern.compile(type.pattern);

			Matcher m = r.matcher(message);

			while (m.find()) {
				stringPositions.add(new StringPosition(m.start(), m.end(), type));
			}
		}

		return stringPositions;
	}

	public boolean shouldExitLoop (ItemStack itemStack) {
		if (itemStack == null || itemStack.getData().getItemType() == Material.AIR || itemStack.getData().getItemType() == Material.LEGACY_AIR) {
			return true;
		}

		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat (AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		String format = event.getFormat();

		PlayerInventory inventory = player.getInventory();

		ItemStack itemInHand = inventory.getItemInMainHand();
		ItemStack itemInOffHand = inventory.getItemInOffHand();

		ItemStack boots = inventory.getBoots();
		ItemStack helmet = inventory.getHelmet();
		ItemStack chestplate = inventory.getChestplate();
		ItemStack legs = inventory.getLeggings();

		TextComponent component = new TextComponent(String.format(format, player.getDisplayName(), ""));

		List<StringPosition> itemPositions = getStringPositions(message);

		if (itemInHand.getData().getItemType() == Material.AIR || itemInHand.getData().getItemType() == Material.LEGACY_AIR) {
			itemInHand = null;
		}

		for (int i = 0;i<itemPositions.size();i++) {
			StringPosition current = itemPositions.get(i);
			StringPosition next = null;

			ItemStack itemToCheck = null;

			switch (current.patternType.type) {
				case "HAND": itemToCheck = itemInHand;
					break;
				case "OFFHAND": itemToCheck = itemInOffHand;
					break;
				case "HELMET": itemToCheck = helmet;
					break;
				case "CHESTPLATE": itemToCheck = chestplate;
					break;
				case "LEGS": itemToCheck = legs;
					break;
				case "BOOTS": itemToCheck = boots;
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

			if (next != null) {
				end = message.substring(current.end, next.start);
			}

			if (i == itemPositions.size()-1) {
				end = message.substring(current.end);
			}

			if (!shouldExitLoop(itemToCheck)) {
				start = start.replaceAll(current.patternType.pattern, "");
				end = end.replaceAll(current.patternType.pattern, "");
			}

			component.addExtra(start);

			component.addExtra(ItemUtil.getItemComponent(itemToCheck, current.patternType.pattern.replace("\\", "")));

			component.addExtra(end);
		}

		if (itemPositions.size() > 0) {
			event.getRecipients().forEach((Player recipient) -> {
				recipient.spigot().sendMessage(component);
			});

			event.setCancelled(true);
		}
	}
}
