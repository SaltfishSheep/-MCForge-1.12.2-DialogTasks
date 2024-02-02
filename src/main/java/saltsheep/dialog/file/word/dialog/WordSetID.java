package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class WordSetID extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "setID";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"ID"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		creator.dialogID = params.get("ID");
	}

}
