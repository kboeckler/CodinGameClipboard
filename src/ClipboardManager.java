import java.io.File;
import java.util.List;

public class ClipboardManager {

  public static void main(String[] args) {
    new ClipboardManager();
  }

  private FileFinder finder;
  private String imports;
  private boolean shallMinify;
  private boolean shallRemovePackage;
  private ClipboardWriter clipboardWriter;
  private MinifyService minifier;

  public ClipboardManager() {
    clipboardWriter = new ClipboardWriter();
    minifier = new MinifyService();
    new SimpleCopyToClipboardGui(this);
  }

  boolean setSourceFolder(String sourceFolder) {
    File folder = new File(sourceFolder);
    if (folder.isDirectory()) {
      finder = new FileFinder(folder);
      return true;
    }
    return false;
  }

  void setShallMinify(boolean shallMinify) {
    this.shallMinify = shallMinify;
  }

  public void setShallRemovePackageDeclaration(boolean shallRemovePackage) {
    this.shallRemovePackage = shallRemovePackage;
  }

  void setImports(String imports) {
    this.imports = imports;
  }

  void executeMergeToClipboard() {
    List<File> files = readFiles();
    String mergedText = mergeTextFromFiles(files);
    mergedText = minifyTextIfWished(mergedText);
    copyTextToClipboard(mergedText);
  }

  private List<File> readFiles() {
    List<File> files = finder.findSourceFiles();
    return files;
  }

  private String mergeTextFromFiles(List<File> files) {
    TextMerger merger = new TextMerger();
    merger.addImportText(imports);
    for (File f : files) {
      String[] ignoreRowsBeginningWith = getIgnoreRows();
      FullFileReader reader = new FullFileReader(f, ignoreRowsBeginningWith);
      merger.addText(reader.readFullText(), f.getName());
    }
    return merger.getMergedText();
  }

  private String[] getIgnoreRows() {
    if (shallRemovePackage) {
      return new String[] {
          "package ",
          "import " };
    }
    return new String[] {
        "import " };
  }

  private String minifyTextIfWished(String sourceText) {
    if (shallMinify) {
      return minifier.minifyIfPossible(sourceText);
    }
    return sourceText;
  }

  private void copyTextToClipboard(String mergedText) {
    clipboardWriter.copyToClipboard(mergedText);
  }

}
