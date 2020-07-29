package xyz.mackan.chatitems.util;

import xyz.mackan.chatitems.types.ChatPattern;

import org.jetbrains.annotations.Nullable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manager class for ChatPatterns
 */
public interface IPatternManager {
	/**
	 * Registered patterns
	 */
	static List<ChatPattern> patterns = new ArrayList<ChatPattern>();

	static final Map<String, ChatPattern> byPattern = new HashMap<String, ChatPattern>();

	static String groupPattern = "";

	/**
	 * Registers a new ChatPattern
	 * @param type The patterns type
	 * @param pattern The regex for the pattern
	 */
	static void registerPattern (ChatPattern.Type type, String pattern) {}

	/**
	 * Registers a new ChatPattern
	 * @param pattern The {@link ChatPattern} to register
	 */
	static void registerPattern (ChatPattern pattern) {}

	/**
	 * Gets a ChatPattern by the regex pattern
	 * @param pattern The pattern to get
	 * @return The {@link ChatPattern} found
	 */
	@Nullable
	static ChatPattern getByPattern (String pattern) { return null; }

	/**
	 * Gets a regex group pattern string with all the registered patterns
	 * @return Group pattern
	 */
	static String getGroups () { return ""; }
}
