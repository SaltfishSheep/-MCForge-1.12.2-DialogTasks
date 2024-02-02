package saltsheep.dialog.file.word.task;

import java.util.Map;

import saltsheep.dialog.file.FormatHelper;
import saltsheep.dialog.file.FormatHelper.MethodFormat;
import saltsheep.dialog.file.task.FileTask;
import saltsheep.dialog.file.word.FFileWordCommonHandlerManager;

public class WordAddRun extends AFileWordTaskHandler{

	@Override
	public String methodWord() {
		return "addRun";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"runTime","run"};
	}

	@Override
	public void handle(Object beHandled, Map<String, String> params, Object... extraParam) {
		if(!(beHandled instanceof FileTask))
			return;
		FileTask task = (FileTask) beHandled;
		MethodFormat requireFormat = FormatHelper.formatMethod(params.get("run"));
		String runTime = params.containsKey("runTime")? params.get("runTime"):"finish";
		FFileWordCommonHandlerManager.handlerRun(task, requireFormat.methodWord, requireFormat.params, runTime);
	}

}
