import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class MinifyService {

	private String input;
	private String output;
	private JSMin jsMinifier;

	MinifyService() {
	}

	String minifyIfPossible(String input) {
		this.input = input;
		try {
			doRequest();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return output;
	}

	private void doRequest() throws Exception {
		InputStream stream = new ByteArrayInputStream(
				input.getBytes(StandardCharsets.UTF_8));
		ByteArrayOutputStream oStream = new ByteArrayOutputStream();
		jsMinifier = new JSMin(stream, oStream);
		jsMinifier.jsmin();
		output = oStream.toString();
	}

}
