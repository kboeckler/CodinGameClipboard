import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ClipboardWriter {

	public ClipboardWriter() {
		// TODO Auto-generated constructor stub
	}

	void copyToClipboard(String text) {
		StringSelection stringSelection = new StringSelection(
				text);
		Clipboard clpbrd = Toolkit.getDefaultToolkit()
				.getSystemClipboard();
		clpbrd.setContents(stringSelection, null);
	}

}
