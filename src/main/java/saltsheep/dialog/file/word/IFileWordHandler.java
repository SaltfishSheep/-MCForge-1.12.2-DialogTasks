package saltsheep.dialog.file.word;

import java.util.Map;

public interface IFileWordHandler {

	public String methodWord();
	
	public String[] paramWords();
	
	public String handlerType();
	
	public void handle(Object beHandled,Map<String,String> params,Object... extraParam);
	
}
