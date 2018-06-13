package itd.filereader.app;

import java.io.File;

public interface IReportStrategy {
	boolean accept(File f);
	void dispose();
}
