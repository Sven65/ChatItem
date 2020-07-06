package xyz.mackan.ChatItem;

public enum PatternType {
	ITEM("HAND", "\\[item\\]"),
	I("HAND", "\\[i\\]"),
	HAND("HAND", "\\[hand\\]"),

	OFFHAND("OFFHAND", "\\[offhand\\]"),

	HELMET("HELMET", "\\[helmet\\]"),
	HELM("HELMET", "\\[helm\\]"),

	CHESTPLATE("CHESTPLATE", "\\[chestplate\\]"),
	CHEST("CHESTPLATE", "\\[chest\\]"),

	LEGS("LEGS", "\\[legs\\]"),
	BOOTS("BOOTS", "\\[boots\\]");


	public final String type;
	public final String pattern;

	private PatternType(String type, String pattern) {
		this.type = type;
		this.pattern = pattern;
	}
}
