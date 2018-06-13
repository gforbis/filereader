package itd.filereader.reader;

import java.io.File;

public interface IAttributeReader<T> {
	T read(File f);
}
