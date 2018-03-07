public class TextMerger {

	private StringBuilder mergedTextBuilder;

	public TextMerger() {
		mergedTextBuilder = new StringBuilder(
				"// ######################## Merged Source Files together ########################");
		mergedTextBuilder.append("\n\n");
	}

	void addText(String text, String source) {
		mergedTextBuilder
				.append("// ######################## Source File: "
						+ source
						+ " ########################\n\n");
		mergedTextBuilder.append(text);
		mergedTextBuilder.append("\n\n");
	}

	void addImportText(String text) {
		mergedTextBuilder
				.append("// ######################## Imports: "
						+ " ########################\n\n");
		mergedTextBuilder.append(text);
		mergedTextBuilder.append("\n\n");
	}

	String getMergedText() {
		return mergedTextBuilder.toString();
	}

}
