package xyz.mackan.ChatItem.events;

import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import xyz.mackan.ChatItem.API.ChatItemsAPI;
import xyz.mackan.ChatItem.PatternType;
import xyz.mackan.ChatItem.StringPosition;
import xyz.mackan.ChatItem.util.ItemUtil;

import javax.xml.soap.Text;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatEventListener implements Listener {

	public List<StringPosition> getStringPositions (String message) {
		Pattern matchPattern = Pattern.compile(PatternType.getGroups());

		List<StringPosition> stringPositions = new ArrayList<StringPosition>();

		Matcher m = matchPattern.matcher(message);

		while (m.find()) {
			String group = m.group();

			PatternType patternType = PatternType.getByPattern(group);

			stringPositions.add(new StringPosition(m.start(), m.end(), patternType));
		}

		return stringPositions;
	}

	public boolean shouldExitLoop (ItemStack itemStack) {
		ChatItemsAPI api = Bukkit.getServicesManager().getRegistration(ChatItemsAPI.class).getProvider();

		if (itemStack == null || api.isAir(itemStack)) {
			return true;
		}

		return false;
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerChat (AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		String format = event.getFormat();

		ChatItemsAPI api = Bukkit.getServicesManager().getRegistration(ChatItemsAPI.class).getProvider();

		ItemStack itemInHand = api.getItemInMainHand(player);
		ItemStack itemInOffHand = api.getItemInOffHand(player);

		ItemStack boots = api.getBoots(player);
		ItemStack helmet = api.getHelmet(player);
		ItemStack chestplate = api.getChestplate(player);
		ItemStack legs = api.getLegs(player);

		TextComponent chatComponent = new TextComponent(String.format(format, player.getDisplayName(), ""));

		List<StringPosition> itemPositions = getStringPositions(message);

		if (api.isAir(itemInHand)) {
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

			String defaultString = "";

			if (next != null) {
				System.out.println("i: "+i);
				end = message.substring(current.end, next.start);
			}

			if (i == itemPositions.size()-1) {
				end = message.substring(current.end);
			}

			start = start.replaceAll(current.patternType.pattern, "");
			end = end.replaceAll(current.patternType.pattern, "");

			if (shouldExitLoop(itemToCheck)) {
				defaultString = current.patternType.pattern.replace("\\", "");
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
