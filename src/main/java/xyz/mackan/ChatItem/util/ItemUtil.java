package xyz.mackan.ChatItem.util;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import xyz.mackan.ChatItem.ChatItem;
import xyz.mackan.ChatItem.util.ReflectionUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.logging.Level;

public class ItemUtil {
	/**
	 * Converts an {@link org.bukkit.inventory.ItemStack} to a Json string
	 * for sending with {@link net.md_5.bungee.api.chat.BaseComponent}'s.
	 *
	 * @param itemStack the item to convert
	 * @return the Json string representation of the item
	 */
	public static String convertItemStackToJson(ItemStack itemStack) {
		// ItemStack methods to get a net.minecraft.server.ItemStack object for serialization
		Class<?> craftItemStackClazz = ReflectionUtil.getOBCClass("inventory.CraftItemStack");
		Method asNMSCopyMethod = ReflectionUtil.getMethod(craftItemStackClazz, "asNMSCopy", ItemStack.class);

		// NMS Method to serialize a net.minecraft.server.ItemStack to a valid Json string
		Class<?> nmsItemStackClazz = ReflectionUtil.getNMSClass("ItemStack");
		Class<?> nbtTagCompoundClazz = ReflectionUtil.getNMSClass("NBTTagCompound");
		Method saveNmsItemStackMethod = ReflectionUtil.getMethod(nmsItemStackClazz, "save", nbtTagCompoundClazz);

		Object nmsNbtTagCompoundObj; // This will just be an empty NBTTagCompound instance to invoke the saveNms method
		Object nmsItemStackObj; // This is the net.minecraft.server.ItemStack object received from the asNMSCopy method
		Object itemAsJsonObject; // This is the net.minecraft.server.ItemStack after being put through saveNmsItem method

		try {
			nmsNbtTagCompoundObj = nbtTagCompoundClazz.newInstance();
			nmsItemStackObj = asNMSCopyMethod.invoke(null, itemStack);
			itemAsJsonObject = saveNmsItemStackMethod.invoke(nmsItemStackObj, nmsNbtTagCompoundObj);
		} catch (Throwable t) {
			Bukkit.getLogger().log(Level.SEVERE, "failed to serialize itemstack to nms item", t);
			return null;
		}

		// Return a string representation of the serialized object
		return itemAsJsonObject.toString();
	}

	public static String getTranslatableMaterialName (ItemStack item) {
		return ChatItem.getLocaleManager().queryMaterial(item.getType());
	}

	public static String getItemMetaName (ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		boolean hasItemMeta = item.hasItemMeta();

		if (!hasItemMeta) {
			return null;
		} else {
			String displayName = meta.getDisplayName();

			if (displayName == null || displayName.equals("")) {
				return null;
			}

			return displayName;
		}
	}
}
