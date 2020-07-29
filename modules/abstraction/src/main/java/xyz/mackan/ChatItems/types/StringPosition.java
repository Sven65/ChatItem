package xyz.mackan.ChatItems.types;

/**
 * Utility class for ChatPattern positions in a string
 */
public class StringPosition {

	/**
	 * Start position of a substring
	 */
	public int start;

	/**
	 * End position of a substring
	 */
	public int end;

	/**
	 * The ChatPattern that was used for the StringPosition
	 */
	public ChatPattern chatPattern;

	/**
	 * Creates a new StringPosition
	 * @param start Integer start position
	 * @param end Integer end position
	 * @param chatPattern ChatPattern that was used
	 */
	public StringPosition (int start, int end, ChatPattern chatPattern) {
		this.start = start;
		this.end = end;
		this.chatPattern = chatPattern;
	}

	/**
	 * Creates a new StringPosition
	 * @param start Integer start position
	 * @param end Integer end position
	 */
	public StringPosition (int start, int end) {
		this.start = start;
		this.end = end;
	}
}
