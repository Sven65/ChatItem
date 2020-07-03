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
import xyz.mackan.ChatItem.ChatItem;
import xyz.mackan.ChatItem.StringPosition;
import xyz.mackan.ChatItem.util.FormatUtil;
import xyz.mackan.ChatItem.util.ItemUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatEventListener implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat (AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		String format = event.getFormat();

		PlayerInventory inventory = player.getInventory();

		ItemStack itemInHand = inventory.getItemInMainHand();

		if (itemInHand == null || itemInHand.getData().getItemType() == Material.AIR) {
			return;
		}

		TextComponent component = new TextComponent(String.format(format, player.getDisplayName(), ""));

		String itemPattern = "\\[item\\]";

		Pattern r = Pattern.compile(itemPattern);

		Matcher m = r.matcher(message);

		int count = 0;

		// I'm sure there's a better way to do this, but this works.
		List<StringPosition> itemPositions = new ArrayList<StringPosition>();

		while (m.find()) {
			count++;
			itemPositions.add(new StringPosition(m.start(), m.end()));
		}


		for (int i = 0;i<itemPositions.size();i++) {
			StringPosition current = itemPositions.get(i);
			StringPosition next = null;

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

			start = start.replaceAll(itemPattern, "");
			end = end.replaceAll(itemPattern, "");

			if (ChatItem.getIsEssChatEnabled()) {
				start = FormatUtil.formatMessage(player, start);
				end = FormatUtil.formatMessage(player, end);
			}

			component.addExtra(start);

			component.addExtra(ItemUtil.getItemComponent(itemInHand));

			component.addExtra(end);
		}

		if (count > 0) {
			event.getRecipients().forEach((Player recipient) -> {
				recipient.spigot().sendMessage(component);
			});

			event.setCancelled(true);
		}
	}
}
