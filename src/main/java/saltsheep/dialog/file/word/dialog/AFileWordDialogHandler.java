package saltsheep.dialog.file.word.dialog;

import saltsheep.dialog.file.word.IFileWordHandler;

public abstract class AFileWordDialogHandler implements IFileWordHandler {
	
	@Override
	public String handlerType() {
		return "Dialog";
	}

}
