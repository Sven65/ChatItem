package xyz.mackan.ChatItems.util;

import org.jetbrains.annotations.Nullable;
import xyz.mackan.ChatItems.types.ChatPattern;

/**
 * Manager class for ChatPatterns
 */
public class PatternManager implements IPatternManager {

	private static String groupPattern = "";

	/**
	 * Registers a new ChatPattern
	 * @param type The patterns type
	 * @param pattern The regex for the pattern
	 */
	public static void registerPattern (ChatPattern.Type type, String pattern) {
		ChatPattern newPattern = new ChatPattern(type, pattern);
		patterns.add(newPattern);
		byPattern.put(pattern.replaceAll("\\\\", ""), newPattern);
	}

	/**
	 * Registers a new ChatPattern
	 * @param pattern The ChatPattern to register
	 */
	public static void registerPattern (ChatPattern pattern) {
		patterns.add(pattern);
		byPattern.put(pattern.pattern.replaceAll("\\\\", ""), pattern);
	}

	/**
	 * Gets a ChatPattern by the regex pattern
	 * @param pattern The pattern to get
	 * @return The ChatPattern found
	 */
	@Nullable
	public static ChatPattern getByPattern (String pattern) {
		return byPattern.get(pattern.replaceAll("\\\\", ""));
	}

	/**
	 * Gets a regex group pattern string with all the registered patterns
	 * @return Group pattern
	 */
	public static String getGroups () {
		if (!groupPattern.equalsIgnoreCase("")) {
			return groupPattern;
		}

		groupPattern = "(";

		int i = 0;

		for (ChatPattern chatPattern : patterns) {
			groupPattern += chatPattern.pattern;

			if (i < patterns.size() - 1) {
				groupPattern += "|";
			}

			i++;
		}

		groupPattern += ")";

		return groupPattern;
	}
}
