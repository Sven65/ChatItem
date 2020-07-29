package xyz.mackan.ChatItems;

import org.jetbrains.annotations.Nullable;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * The main API for ChatItems
 */
public interface ChatItemsAPI {
	/**
	 * Gets the item in the players main hand
	 * @param player The player to get the item from
	 * @return The ItemStack in the players main hand
	 */
	ItemStack getItemInMainHand (Player player);

	/**
	 * Gets the item in the players offhand
	 * @param player The player to get the item from
	 * @return The ItemStack in the players offhand
	 */
	@Nullable
	ItemStack getItemInOffHand (Player player);

	/**
	 * Gets the item in the players boots slot
	 * @param player The player to get the item from
	 * @return The ItemStack in the players boots slot
	 */
	ItemStack getBoots (Player player);

	/**
	 * Gets the item in the players helmet slot
	 * @param player The player to get the item from
	 * @return The ItemStack in the players helmet slot
	 */
	ItemStack getHelmet (Player player);

	/**
	 * Gets the item in the players chestplate slot
	 * @param player The player to get the item from
	 * @return The ItemStack in the players chestplate slot
	 */
	ItemStack getChestplate (Player player);

	/**
	 * Gets the item in the players leg slot
	 * @param player The player to get the item from
	 * @return The ItemStack in the players leg slot
	 */
	ItemStack getLegs (Player player);

	/**
	 * Checks if an ItemStack is air
	 * @param item The item to check
	 * @return true / false depending on if the ItemStack is air
	 */
	boolean isAir (ItemStack item);

	/**
	 * Converts the ItemStack to JSON
	 * @param itemStack The itemStack to convert to JSON
	 * @return The JSON string for the item
	 */
	String convertItemStackToJson(ItemStack itemStack);
}