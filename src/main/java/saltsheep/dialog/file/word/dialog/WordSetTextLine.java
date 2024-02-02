package saltsheep.dialog.file.word.dialog;

import java.util.List;
import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class WordSetTextLine extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "setTextLine";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"line"};
	}

	@SuppressWarnings("unchecked")
	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		int line = Integer.valueOf(params.get("line"));
		creator.dialogTexts = new String[line];
		creator.dialogTimesRaw = new String[line];
		creator.runs = new List[line];
	}

}
