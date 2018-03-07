import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileFinder {

	private File sourceFolder;

	public FileFinder(File sourceFolder) {
		this.sourceFolder = sourceFolder;
	}

	List<File> findSourceFiles() {
		List<File> files = new ArrayList<>();
		for (File f : sourceFolder.listFiles()) {
			if (isJavaSource(f)) {
				files.add(f);
			}
		}
		return files;
	}

	private boolean isJavaSource(File file) {
		String path = file.getName();
		return path.endsWith(".java");
	}

}
