package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class WordSetText extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "setText";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"line","text","delay"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		int line = Integer.valueOf(params.get("line"));
		String text = params.get("text");
		if(creator.dialogTexts==null||line>creator.dialogTexts.length)
			return;
		creator.dialogTexts[line-1] = text;
		if(params.containsKey("delay"))
			creator.dialogTimesRaw[line-1] = params.get("delay");
	}

}
