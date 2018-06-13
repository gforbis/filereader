package itd.filereader.reader;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSizeReader implements IAttributeReader<Long> {
	private static final Pattern pathPattern = Pattern.compile(".*\\\\\\d{4}\\\\\\d{8}\\\\.*\\\\.*");

	public FileSizeReader() {
		
	}

	@Override
	public Long read(File f) {
		Matcher m = pathPattern.matcher(f.getPath());
		return f.exists() && m.matches() ? f.length() : -1;
	}
}
