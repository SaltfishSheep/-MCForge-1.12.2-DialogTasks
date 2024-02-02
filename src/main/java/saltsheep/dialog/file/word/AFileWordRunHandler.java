package saltsheep.dialog.file.word;

import java.util.Map;

import saltsheep.dialog.file.IRunAddable;

public abstract class AFileWordRunHandler implements IFileWordHandler {
	
	@Override
	public String handlerType() {
		return "ExtraRun";
	}

	public abstract IRunAddable.ExtraRun getRun(IRunAddable creator,Map<String,String> params);
	
	@Override
	public void handle(Object addtor,Map<String,String> params,Object... extraParam) {
		if(!(addtor instanceof IRunAddable))
			return;
		IRunAddable iaddtor = ((IRunAddable)addtor);
		iaddtor.addRun(this.getRun(iaddtor, params),extraParam);
	}
	
}
