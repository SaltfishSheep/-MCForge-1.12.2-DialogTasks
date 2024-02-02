package saltsheep.dialog.file.word.task;

import java.util.Map;

import com.google.common.collect.Maps;

import saltsheep.dialog.file.task.FileTask;

public class FFileWordTaskHandlerManager {

	private static Map<String,AFileWordTaskHandler> dialogHandlers = Maps.newHashMap();
	
	public static void register(AFileWordTaskHandler handler) {
		if(handler instanceof AFileWordTaskHandler)
			dialogHandlers.put(handler.methodWord(), (AFileWordTaskHandler) handler);
	}
	
	public static void handlerMain(FileTask task,String methodWord,Map<String,String> params,Object... extraParam) {
		if(!dialogHandlers.containsKey(methodWord))
			return;
		dialogHandlers.get(methodWord).handle(task, params, extraParam);
	}
	
}
