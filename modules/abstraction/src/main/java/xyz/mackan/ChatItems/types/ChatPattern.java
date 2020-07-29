package xyz.mackan.ChatItems.types;

/**
 * Container class for a pattern to resolve
 */
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

	/**
	 * The type the pattern has
	 */
	public final Type type;

	/**
	 * The regex pattern that this pattern resolves
	 */
	public final String pattern;

	/**
	 * Creates a new ChatPattern
	 * @param type {@link ChatPattern.Type} the type of the chatpattern
	 * @param pattern String of the regex pattern
	 */
	public ChatPattern (Type type, String pattern) {
		this.type = type;
		this.pattern = pattern;
	}
}
