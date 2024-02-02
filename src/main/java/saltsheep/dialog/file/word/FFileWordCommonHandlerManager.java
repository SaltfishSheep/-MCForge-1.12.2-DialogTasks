package saltsheep.dialog.file.word;

import java.util.Map;

import com.google.common.collect.Maps;

import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;

public class FFileWordCommonHandlerManager {

	public static Map<String,AFileWordRequireHandler> requireHandlers = Maps.newHashMap();
	public static Map<String,AFileWordRunHandler> runHandlers = Maps.newHashMap();
	
	public static void register(IFileWordHandler handler) {
		if(handler instanceof AFileWordRequireHandler)
			requireHandlers.put(handler.methodWord(), (AFileWordRequireHandler) handler);
		else if(handler instanceof AFileWordRunHandler)
			runHandlers.put(handler.methodWord(), (AFileWordRunHandler) handler);
	}
	
	public static void handlerRequire(IRequireAddable creator,String methodWord,Map<String,String> params,Object... extraParam) {
		if(!requireHandlers.containsKey(methodWord))
			return;
		requireHandlers.get(methodWord).handle(creator, params, extraParam);
	}
	
	public static void handlerRun(IRunAddable creator,String methodWord,Map<String,String> params,Object... extraParam) {
		if(!runHandlers.containsKey(methodWord))
			return;
		runHandlers.get(methodWord).handle(creator, params, extraParam);
	}

}
