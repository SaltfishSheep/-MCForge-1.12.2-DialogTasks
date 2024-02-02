package saltsheep.dialog.file.word.task;

import java.util.Map;

import saltsheep.dialog.file.FormatHelper;
import saltsheep.dialog.file.FormatHelper.MethodFormat;
import saltsheep.dialog.file.task.FileTask;
import saltsheep.dialog.file.word.FFileWordCommonHandlerManager;

public class WordAddRequire extends AFileWordTaskHandler{

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
		if(!(beHandled instanceof FileTask))
			return;
		FileTask task = (FileTask) beHandled;
		String group = params.containsKey("group")? params.get("group"):"default";
		MethodFormat requireFormat = FormatHelper.formatMethod(params.get("require"));
		FFileWordCommonHandlerManager.handlerRequire(task, requireFormat.methodWord, requireFormat.params, group);
	}

}
