package itd.filereader.csv;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

public class CSVUtils {
	private static final char SEP = ',';
	
	private CSVUtils() {
		throw new UnsupportedOperationException();
	}
	
	public static void writeLine(Writer w, List<String> values) throws IOException {
		writeLine(w, values, SEP, ' ');
	}
	
	public static void writeLine(Writer w, List<String> values, char separator) throws IOException {
		writeLine(w, values, separator, ' ');
	}
	
	public static String toValidCSV(final String value) {
		String result = value;
		if (result.contains("\"")) {
			result = result.replaceAll("\\\"", "\\\"\\\"");
		}
		return result;
	}
	
	public static void writeLine(Writer w, List<String> values, char separator, char customQuote) throws IOException {
		if (' ' == separator) {
			separator = SEP;
		}
		StringBuilder sb = new StringBuilder();
		for (String value : values) {
			if (sb.length() > 0) {
				sb.append(separator);
			}
			if (' ' == customQuote) {
				sb.append(toValidCSV(value));
			} else {
				sb.append(customQuote).append(toValidCSV(value)).append(customQuote);
			}
		}
	}
}
