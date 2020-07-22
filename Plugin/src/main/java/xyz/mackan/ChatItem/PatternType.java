package xyz.mackan.ChatItem;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public enum PatternType {
	ITEM("HAND", "\\[item\\]"),
	I("HAND", "\\[i\\]"),
	HAND("HAND", "\\[hand\\]"),

	OFFHAND("OFFHAND", "\\[offhand\\]"),

	HELMET("HELMET", "\\[helmet\\]"),
	HELM("HELMET", "\\[helm\\]"),
	HEAD("HELMET", "\\[head\\]"),

	CHESTPLATE("CHESTPLATE", "\\[chestplate\\]"),
	CHEST("CHESTPLATE", "\\[chest\\]"),

	LEGS("LEGS", "\\[legs\\]"),
	BOOTS("BOOTS", "\\[boots\\]");


	public final String type;
	public final String pattern;

	private static String groupPattern = "";

	private static final Map<String, PatternType> byPattern = new HashMap<String, PatternType>();

	static {
		for (PatternType type : PatternType.values()) {
			byPattern.put(type.pattern.replaceAll("\\\\", ""), type);
		}
	}

	private PatternType(String type, String pattern) {
		this.type = type;
		this.pattern = pattern;
	}

	public static PatternType getByPattern (String pattern) {
		return byPattern.get(pattern);
	}

	public static String getGroups () {
		if (!groupPattern.equalsIgnoreCase("")) {
			return groupPattern;
		}

		groupPattern = "(";

		int i = 0;

		for (PatternType type : PatternType.values()) {
			groupPattern += type.pattern;

			if (i < PatternType.values().length - 1) {
				groupPattern += "|";
			}

			i++;
		}

		groupPattern += ")";

		return groupPattern;
	}
}
