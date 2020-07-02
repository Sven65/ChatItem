package xyz.mackan.ChatItem.events;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import xyz.mackan.ChatItem.ChatItem;
import xyz.mackan.ChatItem.util.ItemUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlayerChatEventListener implements Listener {
	@EventHandler(priority = EventPriority.LOWEST)
	public void onPlayerChat (AsyncPlayerChatEvent event) {
		String message = event.getMessage();
		Player player = event.getPlayer();

		PlayerInventory inventory = player.getInventory();

		ItemStack itemInHand = inventory.getItemInMainHand();

		if (itemInHand == null || itemInHand.getData().getItemType() == Material.AIR) {
			return;
		}





		TextComponent component = new TextComponent(player.getDisplayName()+"> ");

		String itemPattern = "\\[item\\]";

		Pattern r = Pattern.compile(itemPattern);

		Matcher m = r.matcher(message);

		int count = 0;

		int lastIndex = 0;

		while (m.find()) {
			count++;

			String start = message.substring(lastIndex, m.start());
			String end = message.substring(m.end());

			lastIndex = m.end();

			component.addExtra(start);


			BaseComponent item = null;

			String itemMetaName = ItemUtil.getItemMetaName(itemInHand);

			if (itemMetaName != null) {
				item = new TextComponent(itemMetaName);
			} else {
				item = new TranslatableComponent(ItemUtil.getTranslatableMaterialName(itemInHand));

			}

			String itemJson = ItemUtil.convertItemStackToJson(itemInHand);

			BaseComponent[] hoverEventComponents = new BaseComponent[]{
				new TextComponent(itemJson)
			};

			item.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents));
			item.setColor(ChatColor.AQUA);


			component.addExtra(item);

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
