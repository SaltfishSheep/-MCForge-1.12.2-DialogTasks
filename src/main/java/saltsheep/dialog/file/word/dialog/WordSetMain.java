package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class WordSetMain extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "setMain";
	}

	@Override
	public String[] paramWords() {
		return new String[0] ;
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		creator.isMain = true;
	}

}
