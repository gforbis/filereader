package itd.filereader.app;

import itd.filereader.reader.CaseNumberReader;
import itd.filereader.reader.FileNameReader;
import itd.filereader.reader.FileReader;
import itd.filereader.reader.FileSizeReader;
import itd.filereader.reader.FolderReader;
import itd.filereader.reader.IFileReader;

public class FileReaderFactory {
	public IFileReader getReader() {
		return new FileReader(new CaseNumberReader(), new FolderReader(), new FileNameReader(), new FileSizeReader());
	}
}
