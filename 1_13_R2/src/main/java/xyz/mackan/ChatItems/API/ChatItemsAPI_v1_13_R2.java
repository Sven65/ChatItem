package xyz.mackan.ChatItems.API;

import net.minecraft.server.v1_13_R2.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import xyz.mackan.chatitems.ChatItemsAPI;

public class ChatItemsAPI_v1_13_R2 implements ChatItemsAPI {
	public ChatItemsAPI_v1_13_R2 () {}

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

	public String convertItemStackToJson (ItemStack itemStack) {
		net.minecraft.server.v1_13_R2.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

		NBTTagCompound nbtTagCompound = new NBTTagCompound();

		nmsStack.save(nbtTagCompound);

		return nbtTagCompound.toString();
	}
}
