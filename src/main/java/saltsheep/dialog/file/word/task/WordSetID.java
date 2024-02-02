package saltsheep.dialog.file.word.task;

import java.util.Map;

import saltsheep.dialog.file.task.FileTask;

public class WordSetID extends AFileWordTaskHandler{

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
		if(!(beHandled instanceof FileTask))
			return;
		FileTask task = (FileTask) beHandled;
		task.taskID = params.get("ID");
	}

}
