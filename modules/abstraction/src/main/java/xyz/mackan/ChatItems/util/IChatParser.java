package xyz.mackan.ChatItems.util;

import xyz.mackan.ChatItems.types.StringPosition;

import java.util.List;

/**
 * Chat parsing utilities
 */
public interface IChatParser {
	/**
	 * Gets the string positions of item placeholders from a message
	 * @param message The message to parse
	 * @return List of {@link StringPosition}s
	 */
	static List<StringPosition> getStringPositions (String message) { return null; }
}
