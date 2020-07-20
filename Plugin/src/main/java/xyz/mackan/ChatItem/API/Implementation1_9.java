package xyz.mackan.ChatItem.API;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Implementation1_9 {
	public Implementation1_9 () {}

	public ItemStack getItemInMainHand (Player player) {
		return player.getInventory().getItemInMainHand();
	}

	public ItemStack getItemInOffHand (Player player) {
		return player.getInventory().getItemInOffHand();
	}

	public ItemStack getBoots (Player player) {
		return player.getInventory().getBoots();
	}

	public ItemStack getHelmet (Player player) {
		return player.getInventory().getHelmet();
	}

	public ItemStack getChestplate (Player player) {
		return player.getInventory().getChestplate();
	}

	public ItemStack getLegs (Player player) {
		return player.getInventory().getLeggings();
	}

	public boolean isAir (ItemStack item) {
		return item.getData().getItemType() == Material.AIR;
	}
}
