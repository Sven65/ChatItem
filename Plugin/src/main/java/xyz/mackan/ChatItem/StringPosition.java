package xyz.mackan.ChatItem;

public class StringPosition {
	public int start;
	public int end;

	public PatternType patternType;

	public StringPosition (int start, int end, PatternType patternType) {
		this.start = start;
		this.end = end;
		this.patternType = patternType;
	}

	public StringPosition (int start, int end) {
		this.start = start;
		this.end = end;
	}
}
