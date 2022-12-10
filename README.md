# CodinGameClipboard

This utility merges multiple java files into one file so it can be easily pasted into the CodinGame editor.

## Usage

With the assembled **CodinGameClipboard.jar**, you can run the tool

### Console interface

Usually just call

``` java -jar CodinGameClipboard.jar <src-folder> ```

You can also run the tool directly with Gradle buildsystem:

``` ./gradlew run --args="<src-folder>"```
'

The arguments passed to the tool are as follows:

``` java -jar CodinGameClipboard.jar [-param] <src-folder>```

with all optional parameters:

- ``` -m ``` to get a minified output
- ``` -p ``` to remove the package if your source folder is not the unnamed root package (the result must be package less for CodinGame)
- ``` -c ``` to directly copy the result to your clipboard (might be not working depending on OS or WSL setups or something). Best Practice would be omitting this parameter and just piping the output to your local clipboard. 
- ``` -i=<imports> ``` to replace the default import statements of this tool with you own set of imports. Since the result file may only declare import once, this is done globally. Specify your imports by just naming the import path, separated by semicolon like so: ``` -i=java.util.*;java.io.*; ```

### Graphical user interface

The tool was first designed to run in a window. Since I now dislike GUIs, this is not developed any further. But it still works. Just run the tool without any arguments to boot the GUI.

``` java -jar CodinGameClipboard.jar ```

## Assembling the tool

You can build the **CodinGameClipboard.jar** by simple calling

``` ./gradlew assemble ```

The file will appear in _build/libs/_ directory.

## Limitations

- Files can only reside in exactly **one** source folder. You cannot have multiple directories / packages.
- Import statements are removed from input files. You need to specify all imports manually for the result. Usually, the default import statements are sufficient for CodinGame.