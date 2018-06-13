package itd.filereader.reader;

import java.io.File;

public class FileReader implements IFileReader {
	private final IAttributeReader<String> caseNumberReader;
	private final IAttributeReader<String> folderReader;
	private final IAttributeReader<String> fileNameReader;
	private final IAttributeReader<Long> fileSizeReader;
	
	public FileReader(IAttributeReader<String> caseNumberReader, IAttributeReader<String> folderReader, IAttributeReader<String> fileNameReader, IAttributeReader<Long> fileSizeReader) {
		this.caseNumberReader = caseNumberReader;
		this.folderReader = folderReader;
		this.fileNameReader = fileNameReader;
		this.fileSizeReader = fileSizeReader;
	}

	@Override
	public IFileMetadata read(File f) {
		String caseNumber = caseNumberReader.read(f);
		String folder = folderReader.read(f);
		String fileName = fileNameReader.read(f);
		long fileSize = fileSizeReader.read(f);
		
		return new FileMetadata(caseNumber, folder, fileName, fileSize);
	}

}
