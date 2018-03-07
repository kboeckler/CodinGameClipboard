import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FullFileReader {

	private File file;
	private String[] ignores;

	public FullFileReader(File file) {
		this(file, new String[0]);
	}

	public FullFileReader(File file, String... ignores) {
		this.file = file;
		this.ignores = ignores;
	}

	String readFullText() {
		StringBuilder fullText = new StringBuilder();
		try (BufferedReader br = new BufferedReader(
				new FileReader(file))) {
			String row = null;
			while ((row = br.readLine()) != null) {
				boolean ignoreRow = false;
				for (String ignore : ignores) {
					if (row.startsWith(ignore)) {
						ignoreRow = true;
						continue;
					}
				}
				if (!ignoreRow) {
					fullText.append(row + "\n");
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		return fullText.toString();
	}

}
