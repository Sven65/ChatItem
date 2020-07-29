package xyz.mackan.chatitems.util;

import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * Utilities for Items
 */
public interface IItemUtil {
	/**
	 * Gets the translatable item material name
	 * @param item ItemStack to get the name of
	 * @return String
	 */
	static String getTranslatableMaterialName (ItemStack item) { return null; }

	/**
	 * Gets the displayname of the item if it has one
	 * @param item ItemStack to get the name of
	 * @return String
	 */
	@Nullable
	static String getItemMetaName (ItemStack item) { return null; }

	/**
	 * Gets the items display name
	 * @param item ItemStack to get the name of
	 * @param returnDefaultName boolean if the items default name should be returned if it doesn't have a display name
	 * @return The display name of the item
	 */
	@Nullable
	static String getItemMetaName (ItemStack item, boolean returnDefaultName) { return null; }

	/**
	 * Gets a chat component for the item to use in chat
	 * @param itemStack ItemStack to get the component for
	 * @param defaultString String the string to use if the itemStack is null
	 * @return BaseComponent
	 */
	static BaseComponent getItemComponent (ItemStack itemStack, String defaultString) { return null; }
}
