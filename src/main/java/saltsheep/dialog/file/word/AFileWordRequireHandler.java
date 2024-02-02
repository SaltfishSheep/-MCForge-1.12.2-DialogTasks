package saltsheep.dialog.file.word;

import java.util.Map;

import saltsheep.dialog.file.IRequireAddable;

public abstract class AFileWordRequireHandler implements IFileWordHandler {
	
	@Override
	public String handlerType() {
		return "Require";
	}

	public abstract IRequireAddable.Requirement getRequire(IRequireAddable addtor,Map<String,String> params);
	
	@Override
	public void handle(Object addtor,Map<String,String> params,Object... extraParam) {
		if(!(addtor instanceof IRequireAddable))
			return;
		((IRequireAddable)addtor).addRequire(this.getRequire((IRequireAddable) addtor, params), extraParam);
	}
	
}
