package xyz.mackan.ChatItems.util;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import xyz.mackan.ChatItems.ChatItems;
import xyz.mackan.ItemNames.ItemNames;
import xyz.mackan.ChatItems.ChatItemsAPI;
import org.jetbrains.annotations.Nullable;

/**
 * Utilities for Items
 */
public class ItemUtil implements IItemUtil {

	/**
	 * Gets the translatable item material name
	 * @param item ItemStack to get the name of
	 * @return String
	 */
	public static String getTranslatableMaterialName (ItemStack item) {
		return ChatItems.getLocaleManager().queryMaterial(item.getType());
	}

	/**
	 * Gets the displayname of the item if it has one
	 * @param item ItemStack to get the name of
	 * @return String
	 */
	@Nullable
	public static String getItemMetaName (ItemStack item) {
		return getItemMetaName(item, false);
	}

	/**
	 * Gets the items display name
	 * @param item ItemStack to get the name of
	 * @param returnDefaultName boolean if the items default name should be returned if it doesn't have a display name
	 * @return The items display name
	 */
	public static String getItemMetaName (ItemStack item, boolean returnDefaultName) {
		ItemMeta meta = item.getItemMeta();
		boolean hasItemMeta = item.hasItemMeta();

		if (!hasItemMeta) {
			if (!returnDefaultName) return null;

			return getItemName(item);
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

	/**
	 * Gets the items name from server locale
	 * @param item ItemStack to get the name of
	 * @return The name of the item from server locale
	 */
	@Nullable
	public static String getItemName (ItemStack item) {
		return ItemNames.getItemName(item);
	}

	/**
	 * Gets a chat component for the item to use in chat
	 * @param itemStack ItemStack to get the component for
	 * @param defaultString String the string to use if the itemStack is null
	 * @return BaseComponent
	 */
	public static BaseComponent getItemComponent (ItemStack itemStack, String defaultString) {
		if (itemStack == null) {
			return new TextComponent(defaultString);
		}

		BaseComponent displayItem;

		int itemAmount = itemStack.getAmount();

		String itemMetaName = getItemMetaName(itemStack);
		String translatableName = getTranslatableMaterialName(itemStack);

		if (itemMetaName != null) {
			String displayName = itemMetaName;

			if (itemAmount == 1 && ChatItems.configHolder.singleItems) {
				displayName = "1 x "+itemMetaName;
			}

			if (itemAmount > 1 && ChatItems.configHolder.multiple) {
				displayName = itemAmount + " x "+itemMetaName;
			}

			displayItem = new TextComponent(displayName);
		} else {
			BaseComponent itemComponent = new TextComponent("");

			if (itemAmount == 1 && ChatItems.configHolder.singleItems) {
				itemComponent = new TextComponent("1 x ");
			}

			if (itemAmount > 1 && ChatItems.configHolder.multiple) {
				itemComponent = new TextComponent(""+itemAmount+" x ");
			}

			itemComponent.addExtra(new TranslatableComponent(translatableName));

			displayItem = itemComponent;
		}

		ChatItemsAPI api = ChatItems.getApi();

		String itemJson = api.convertItemStackToJson(itemStack);

		BaseComponent[] hoverEventComponents = new BaseComponent[]{
				new TextComponent(itemJson)
		};

		displayItem.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents));
		displayItem.setColor(ChatColor.AQUA);

		return displayItem;
	}
}
