package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.dialog.FileDialogCreator;
import saltsheep.dialog.file.dialog.FileDialogInterface;

public class WordAddNext extends AFileWordDialogHandler{

	@Override
	public String methodWord() {
		return "addNext";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"text","next"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		String text = params.get("text");
		String next = params.get("next");
		creator.nextChains.add(new FileDialogInterface(text,next));
	}

}
