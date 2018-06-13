package itd.filereader.filetree;

import java.io.File;

import itd.filereader.app.IReportStrategy;

public class TreeWalker implements ITreeWalker {
	private final IReportStrategy strategy;
	public TreeWalker(IReportStrategy strategy) {
		this.strategy = strategy;
	}

	@Override
	public void walk(File source) {
		for (File f : source.listFiles()) {
			if (f.isDirectory()) {
				walk(f);
			} else {
				strategy.accept(f);
			}
		}
	}
}
