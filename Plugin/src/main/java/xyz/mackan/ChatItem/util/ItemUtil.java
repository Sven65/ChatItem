package xyz.mackan.ChatItem.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.mackan.ChatItem.API.ChatItemsAPI;
import xyz.mackan.ChatItem.ChatItem;

import java.lang.reflect.Method;
import java.util.logging.Level;

public class ItemUtil {
	public static String getTranslatableMaterialName (ItemStack item) {
		return ChatItem.getLocaleManager().queryMaterial(item.getType());
	}

	public static String getItemMetaName (ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		boolean hasItemMeta = item.hasItemMeta();

		if (!hasItemMeta) {
			return null;
		} else {
			if (item.getType() == Material.WRITTEN_BOOK) {
				BookMeta bookMeta = (BookMeta) meta;
				return bookMeta.getTitle();
			}

			String displayName = meta.getDisplayName();

			if (displayName == null || displayName.equals("")) {
				return null;
			}

			return displayName;
		}
	}

	public static BaseComponent getItemComponent (ItemStack itemStack, String noItem) {
		if (itemStack == null) {
			return new TextComponent(noItem);
		}

		int itemAmount = itemStack.getAmount();

		BaseComponent item = null;

		String itemMetaName = ItemUtil.getItemMetaName(itemStack);

		if (itemMetaName != null) {
			String displayName = itemMetaName;

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				displayName = "1 x "+itemMetaName;
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				displayName = itemAmount + " x "+itemMetaName;
			}

			item = new TextComponent(displayName);
		} else {
			BaseComponent itemComponent = new TextComponent();

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				itemComponent = new TextComponent("1 x ");
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				itemComponent = new TextComponent(""+itemAmount+" x ");
			}

			itemComponent.addExtra(new TranslatableComponent(ItemUtil.getTranslatableMaterialName(itemStack)));

			item = itemComponent;
		}

		ChatItemsAPI api = Bukkit.getServicesManager().getRegistration(ChatItemsAPI.class).getProvider();

//		Object itemJson = api.getItemComponent(itemStack);//ItemUtil.convertItemStackToJson(itemStack);
//
//		BaseComponent[] hoverEventComponents = new BaseComponent[]{
//				new TextComponent((BaseComponent)itemJson)
//		};
//
//		item.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents));
//		item.setColor(ChatColor.AQUA);

		return item;
	}
}