package itd.filereader.reader;

import java.io.File;

public interface IFileReader {
	IFileMetadata read(File f);
}
