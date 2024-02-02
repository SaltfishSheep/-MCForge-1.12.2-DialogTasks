package saltsheep.dialog.file.word.dialog;

import java.util.Map;

import com.google.common.collect.Maps;

import saltsheep.dialog.file.dialog.FileDialogCreator;

public class FFileWordDialogHandlerManager {

	private static Map<String,AFileWordDialogHandler> dialogHandlers = Maps.newHashMap();
	
	public static void register(AFileWordDialogHandler handler) {
		if(handler instanceof AFileWordDialogHandler)
			dialogHandlers.put(handler.methodWord(), (AFileWordDialogHandler) handler);
	}
	
	public static void handlerMain(FileDialogCreator creator,String methodWord,Map<String,String> params,Object... extraParam) {
		if(!dialogHandlers.containsKey(methodWord))
			return;
		dialogHandlers.get(methodWord).handle(creator, params, extraParam);
	}
	
}
