package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.FormatHelper;
import saltsheep.dialog.file.FormatHelper.MethodFormat;
import saltsheep.dialog.file.dialog.FileDialogCreator;
import saltsheep.dialog.file.word.FFileWordCommonHandlerManager;

public class WordAddRun extends AFileWordDialogHandler {

	@Override
	public String methodWord() {
		return "addRun";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"line","run"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		MethodFormat runFormat = FormatHelper.formatMethod(params.get("run"));
		int line = Integer.valueOf(params.get("line"));
		FFileWordCommonHandlerManager.handlerRun(creator, runFormat.methodWord, runFormat.params, line);
	}

}
