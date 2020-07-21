package xyz.mackan.ChatItem.API;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.TranslatableComponent;
import net.minecraft.server.v1_8_R1.*;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.mackan.ChatItem.ChatItem;
import xyz.mackan.ChatItem.util.ItemUtil;

import java.lang.reflect.InvocationTargetException;

public class ChatItemsAPI_v1_8_R1 implements ChatItemsAPI {
	public ChatItemsAPI_v1_8_R1 () {}

	public ItemStack getItemInMainHand (Player player) {
		return player.getInventory().getItemInHand();
	}

	public ItemStack getItemInOffHand (Player player) {
		return null;
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
		net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);

		NBTTagCompound nbtTagCompound = new NBTTagCompound();

		nmsStack.save(nbtTagCompound);

		return nbtTagCompound.toString();
	}

	public Object getItemComponent (ItemStack itemStack, String defaultString) {
		if (itemStack == null) {
			return new TextComponent("");
		}

		BaseComponent displayItem;

		int itemAmount = itemStack.getAmount();

		String itemMetaName = ItemUtil.getItemMetaName(itemStack);
		String translatableName = ItemUtil.getTranslatableMaterialName(itemStack);

		net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);


		if (itemMetaName != null) {
			String displayName = itemMetaName;

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				displayName = "1 x "+itemMetaName;
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				displayName = itemAmount + " x "+itemMetaName;
			}

			displayItem = new TextComponent(displayName);
		} else {
			BaseComponent itemComponent = new TextComponent("");

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				itemComponent = new TextComponent("1 x ");
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				itemComponent = new TextComponent(""+itemAmount+" x ");
			}

			itemComponent.addExtra(new TranslatableComponent(translatableName));

			displayItem = itemComponent;
		}

		String itemJson = convertItemStackToJson(itemStack);

		BaseComponent[] hoverEventComponents = new BaseComponent[]{
				new TextComponent(itemJson)
		};

		displayItem.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_ITEM, hoverEventComponents));
		displayItem.setColor(ChatColor.AQUA);

		return displayItem;
	}

	public Object getChatBase () {
		return new TextComponent("");
	}

	public void addExtra (Object chatComponent, Object extra) {
		BaseComponent base = (BaseComponent) chatComponent;

		BaseComponent sibling;

		if (extra instanceof String) {
			sibling = new TextComponent((String)extra);
		} else {
			sibling = (BaseComponent) extra;
		}

		base.addExtra(sibling);
	}

	public void addHoverItem (Object chatComponent, ItemStack item, String defaultString) {
		BaseComponent base = (BaseComponent) chatComponent;

		base.addExtra((BaseComponent) getItemComponent(item, defaultString));
	}

	public void sendMessage (Player player, String format, Object chatComponent) {
		BaseComponent base = (BaseComponent) chatComponent;

		TextComponent layout = new TextComponent(String.format(format, player.getDisplayName(), ""));

		layout.addExtra(base);

		player.spigot().sendMessage(layout);
	}
}
