package xyz.mackan.ChatItem.API;

import net.minecraft.server.v1_8_R1.NBTTagCompound;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

public class ItemAPI_v1_8_R1 implements ItemAPI {
	public String convertItemStackToJson (ItemStack itemStack) {
		net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

		NBTTagCompound nbtTagCompound = new NBTTagCompound();

		nmsStack.save(nbtTagCompound);

		return nmsStack.toString();
	}
}
