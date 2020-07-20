package xyz.mackan.ChatItem.API;

import net.md_5.bungee.api.chat.BaseComponent;
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

	String convertItemStackToJson(ItemStack itemStack);

	Object getItemComponent (ItemStack itemStack, String defaultString);

	Object getChatBase ();

	void addExtra (Object chatComponent, Object extra);

	void addHoverItem (Object chatComponent, ItemStack item, String defaultString);

	void sendMessage (Player player, String format, Object chatComponent);
}
