package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import saltsheep.dialog.file.FormatHelper;
import saltsheep.dialog.file.FormatHelper.MethodFormat;
import saltsheep.dialog.file.dialog.FileDialogCreator;
import saltsheep.dialog.file.word.FFileWordCommonHandlerManager;

public class WordAddRequire extends AFileWordDialogHandler {

	@Override
	public String methodWord() {
		return "addRequire";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"group","require"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileDialogCreator))
			return;
		FileDialogCreator creator = (FileDialogCreator) beHandled;
		String group = params.containsKey("group")? params.get("group"):"default";
		MethodFormat requireFormat = FormatHelper.formatMethod(params.get("require"));
		FFileWordCommonHandlerManager.handlerRequire(creator, requireFormat.methodWord, requireFormat.params, group);
	}

}
