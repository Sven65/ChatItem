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
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players main hand
	 */
	ItemStack getItemInMainHand (Player player);

	/**
	 * Gets the item in the players offhand
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players offhand
	 */
	@Nullable
	ItemStack getItemInOffHand (Player player);

	/**
	 * Gets the item in the players boots slot
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players boots slot
	 */
	ItemStack getBoots (Player player);

	/**
	 * Gets the item in the players helmet slot
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players helmet slot
	 */
	ItemStack getHelmet (Player player);

	/**
	 * Gets the item in the players chestplate slot
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players chestplate slot
	 */
	ItemStack getChestplate (Player player);

	/**
	 * Gets the item in the players leg slot
	 * @param player {@link Player} The player to get the item from
	 * @return {@link ItemStack} The ItemStack in the players leg slot
	 */
	ItemStack getLegs (Player player);

	/**
	 * Checks if an ItemStack is air
	 * @param item {@link ItemStack} The item to check
	 * @return boolean depending on if the ItemStack is air
	 */
	boolean isAir (ItemStack item);

	/**
	 * Converts the ItemStack to JSON
	 * @param itemStack {@link ItemStack} The itemStack to convert to JSON
	 * @return String The JSON string for the item
	 */
	String convertItemStackToJson(ItemStack itemStack);
}