package itd.filereader.app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import itd.filereader.csv.CSVUtils;
import itd.filereader.reader.IFileMetadata;
import itd.filereader.reader.IFileReader;

public class ExportReport implements IReportStrategy {
	private static final int ROWS_PER_FILE = 250000;
	private final IFileReader fileReader;
	private final String exportFormatter;
	private int fileSequence = 0;
	private int currentRow = 0;
	private FileWriter writer = null;

	public ExportReport(IFileReader fileReader, String exportFormatter) {
		this.fileReader = fileReader;
		this.exportFormatter = exportFormatter;
	}
	
	private FileWriter getWriter() throws IOException {
		if (null == writer) {
			File f = new File(String.format(exportFormatter,  fileSequence));
			while (f.exists()) {
				f = new File(String.format(exportFormatter,  ++fileSequence));
			}
			System.out.println("Creating file: " + f.getPath());
			writer = new FileWriter(f.getPath());
			CSVUtils.writeLine(writer,  Arrays.asList("Case Number", "Folder", "File Name", "Size"));
		}
		return writer;
	}

	@Override
	public boolean accept(File f) {
		boolean isAccepted = false;
		IFileMetadata data = fileReader.read(f);
		if (null != data.getCaseNumber() && null != data.getFolder() && null != data.getFileName()) {
			isAccepted = true;
			List<String> values = Arrays.asList(data.getCaseNumber(), data.getFolder(), data.getFileName(), String.format("%1d12",  data.getSize()));
			try {
				CSVUtils.writeLine(getWriter(), values);
				currentRow++;
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		if (ROWS_PER_FILE == currentRow) {
			dispose();
		}
		return isAccepted;
	}

	@Override
	public void dispose() {
		try {
			if (null != writer) {
				System.out.println("Current file closed.");
				writer.flush();
				writer.close();
			}
			writer = null;
			currentRow = 0;
			fileSequence++;
		} catch (IOException e) {
			System.err.println(e);
		}

	}

}
