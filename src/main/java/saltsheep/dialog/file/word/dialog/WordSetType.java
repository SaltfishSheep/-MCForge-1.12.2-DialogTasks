package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class WordSetType extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "setType";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"type"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		creator.dialogType = params.get("type");
	}

}
