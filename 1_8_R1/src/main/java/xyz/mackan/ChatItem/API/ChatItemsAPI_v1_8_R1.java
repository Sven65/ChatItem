package xyz.mackan.ChatItem.API;

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
			return new ChatComponentText(defaultString);
		}

		IChatBaseComponent displayItem;

		int itemAmount = itemStack.getAmount();

		String itemMetaName = ItemUtil.getItemMetaName(itemStack);
//		String translatableName = ItemUtil.getTranslatableMaterialName(itemStack);

		net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);


		if (itemMetaName != null) {
			String displayName = itemMetaName;

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				displayName = "1 x "+itemMetaName;
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				displayName = itemAmount + " x "+itemMetaName;
			}

			displayItem = new ChatComponentText(displayName);
		} else {
			IChatBaseComponent itemComponent = new ChatComponentText("");

			if (itemAmount == 1 && ChatItem.configHolder.singleItems) {
				itemComponent = new ChatComponentText("1 x ");
			}

			if (itemAmount > 1 && ChatItem.configHolder.multiple) {
				itemComponent = new ChatComponentText(""+itemAmount+" x ");
			}

			// TODO: Translate items in 1.8
//			ChatComponentText translatableItem = new ChatComponentText(",{\"translate\":\"" + translatableName + "\"},");
//
//			itemComponent.addSibling(translatableItem);

			ChatComponentText item = new ChatComponentText(nmsStack.getName());

			itemComponent.addSibling(item);

			displayItem = itemComponent;
		}

		ChatModifier modifier = displayItem.getChatModifier();

		modifier.setColor(EnumChatFormat.AQUA);

		ChatHoverable hoverAction = new ChatHoverable(
				EnumHoverAction.SHOW_ITEM,
				(IChatBaseComponent)new ChatComponentText(convertItemStackToJson(itemStack))
		);

		modifier.setChatHoverable(hoverAction);

		displayItem.setChatModifier(modifier);

		return displayItem;
	}

	public Object getChatBase () {
		return new ChatComponentText("");
	}

	public void addExtra (Object chatComponent, Object extra) {
		IChatBaseComponent base = (IChatBaseComponent) chatComponent;

		IChatBaseComponent sibling;

		if (extra instanceof String) {
			sibling = new ChatComponentText((String)extra);
		} else {
			sibling = (IChatBaseComponent) extra;
		}

		base.addSibling(sibling);
	}

	public void addHoverItem (Object chatComponent, ItemStack item, String defaultString) {
		IChatBaseComponent base = (IChatBaseComponent) chatComponent;

		base.addSibling((IChatBaseComponent) getItemComponent(item, defaultString));
	}

	public void sendMessage (Player player, String format, Object chatComponent) {
		IChatBaseComponent base = (IChatBaseComponent) chatComponent;

		ChatComponentText layout = new ChatComponentText(String.format(format, player.getDisplayName(), ""));

		layout.addSibling(base);

		EntityPlayer entityPlayer = (EntityPlayer) ((CraftPlayer) player).getHandle();

		entityPlayer.sendMessage(layout);
	}
}
