package xyz.mackan.ChatItem.API;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public interface ChatItemsAPI {
	ItemStack getItemInMainHand (Player player);

	ItemStack getItemInOffHand (Player player);

	ItemStack getBoots (Player player);

	ItemStack getHelmet (Player player);

	ItemStack getChestplate (Player player);

	ItemStack getLegs (Player player);

	boolean isAir (ItemStack item);
}
