package saltsheep.dialog.file.word.task;

import saltsheep.dialog.file.word.IFileWordHandler;

public abstract class AFileWordTaskHandler implements IFileWordHandler {
	
	@Override
	public String handlerType() {
		return "Task";
	}

}
