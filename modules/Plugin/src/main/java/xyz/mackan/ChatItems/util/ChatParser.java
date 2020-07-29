package xyz.mackan.ChatItems.util;

import xyz.mackan.chatitems.types.ChatPattern;
import xyz.mackan.chatitems.types.StringPosition;
import xyz.mackan.chatitems.util.IChatParser;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatParser implements IChatParser {
	/**
	 * Gets the string positions of item placeholders from a message
	 * @param message The message to parse
	 * @return List of string positions
	 */
	public static List<StringPosition> getStringPositions (String message) {
		Pattern matchPattern = Pattern.compile(PatternManager.getGroups());

		List<StringPosition> stringPositions = new ArrayList<StringPosition>();

		Matcher m = matchPattern.matcher(message);

		while (m.find()) {
			String group = m.group();

			ChatPattern chatPattern = PatternManager.getByPattern(group);

			stringPositions.add(new StringPosition(m.start(), m.end(), chatPattern));
		}

		return stringPositions;
	}
}
