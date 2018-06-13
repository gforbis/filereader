package itd.filereader.reader;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FolderReader implements IAttributeReader<String> {
	private static final Pattern pathPattern = Pattern.compile(".*\\\\\\d{4}\\\\\\d{8}\\\\(.*)\\\\.*");

	public FolderReader() {
		
	}

	@Override
	public String read(File f) {
		Matcher m = pathPattern.matcher(f.getPath());
		return m.matches() ? m.group(1) : null;
	}

}
