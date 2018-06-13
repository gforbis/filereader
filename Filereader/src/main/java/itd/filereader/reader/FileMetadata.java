package itd.filereader.reader;

public class FileMetadata implements IFileMetadata {
	private final String caseNumber;
	private final String folder;
	private final String fileName;
	private final long fileSize;
	public FileMetadata(String caseNumber, String folder, String fileName, long fileSize) {
		this.caseNumber = caseNumber;
		this.folder = folder;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}

	@Override
	public String getFolder() {
		return folder;
	}

	@Override
	public String getCaseNumber() {
		return caseNumber;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public long getSize() {
		return fileSize;
	}

}
