package itd.filereader.app;

import java.io.File;
import java.util.concurrent.TimeUnit;

import itd.filereader.filetree.ITreeWalker;
import itd.filereader.filetree.TreeWalker;
import itd.filereader.reader.IFileReader;

public class Main {
	public static void main(String[] args) {
		String rootPath = null;
		String exportPath = null;
		int max_retries = 0;
		if (args.length == 0) {
			printHelp();
			return;
		}
		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if ("-?".equals(arg)) {
				printHelp();
				return;
			}
			if ("-rootpath".equalsIgnoreCase(arg)) {
				if (i+1 < args.length) {
					rootPath = args[++i];
				} else {
					System.out.println("Missing switch value: -rootpath");
					return;
				}
			} else if ("-exportpath".equalsIgnoreCase(arg)) {
				if (i+1 < args.length) {
					exportPath = args[++i];
				} else {
					System.out.println("Missing switch value: -exportpath");
					return;
				}
			} else if ("-retries".equalsIgnoreCase(arg)) {
				if (i+1 < args.length) {
					max_retries = Integer.parseInt(args[++i]);
				} else {
					System.out.println("Missing switch value: -retries");
					return;
				}
			}
		}
		if (null == rootPath) {
			System.out.println("Missing required argument: -rootpath");
			return;
		}
		if (null == exportPath) {
			exportPath = ".\\files.csv";
		}
		int attempt = 0;
		IFileReader fileReader = new FileReaderFactory().getReader();
		IReportStrategy report = new ExportReport(fileReader, exportPath);
		File root = new File(rootPath);
		System.out.println("Starting");
		try {
			while ((null == root || !root.exists()) && attempt < max_retries) {
				System.out.println("Source unavailable. Retrying in 5 minutes.");
				Thread.sleep(TimeUnit.MINUTES.toMillis(5));
				root = new File(rootPath);
				attempt++;
			}
			if (null != root && root.exists()) {
				ITreeWalker tree = new TreeWalker(report);
				tree.walk(root);
			} else {
				System.err.println("Source never became available.");
			}
		} catch (InterruptedException e) {
			System.err.println(e);
		}
		report.dispose();
		System.out.println("Finished");
	}

	private static void printHelp() {
		System.out.println("Switches:");
		System.out.println("-rootpath : [required] the location to read");
		System.out.println("            required structure underneath is \\year\\casenumber\\<folder>\\<file>");
		System.out.println("  ex: -rootpath \\\\FileServer\\folder\n");
		System.out.println("-exportpath: [.\\files.csv] the file name to be exported (must end in .csv)");
		System.out.println("  ex: -exportpath C:\\Users\\Me\\FileReaderFiles\\export.csv\n");
		System.out.println("- retries: [0] the number of times to retry in case root is unavailable. Each retry waits 5 minutes.");
		System.out.println("  ex: -retries 5");
	}
}
