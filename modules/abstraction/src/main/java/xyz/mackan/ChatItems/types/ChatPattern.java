package xyz.mackan.ChatItems.types;

public class ChatPattern {
	/**
	 * Inventory slots the pattern corresponds to
	 */
	public enum Type {
		HAND,
		OFFHAND,
		HELMET,
		CHESTPLATE,
		LEGS,
		BOOTS,
	}

	public final Type type;
	public final String pattern;

	public ChatPattern (Type type, String pattern) {
		this.type = type;
		this.pattern = pattern;
	}
}
