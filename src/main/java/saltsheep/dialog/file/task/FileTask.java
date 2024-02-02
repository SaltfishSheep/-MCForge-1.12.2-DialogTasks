package saltsheep.dialog.file.task;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;;

public class FileTask extends ATask implements IRequireAddable, IRunAddable {

	public Map<String,List<IRequireAddable.Requirement>> finishRequires = Maps.newHashMap();
	public List<IRunAddable.ExtraRun> startRuns = Lists.newLinkedList();
	public List<IRunAddable.ExtraRun> finishRuns = Lists.newLinkedList();
	
	public String taskID = null;
	
	@Override
	public String getID() {
		return this.taskID;
	}

	@Override
	protected void startRun(EntityPlayerMP player) {
		for(ExtraRun run:this.startRuns)
			run.run(player, null);
	}

	@Override
	protected void finishRun(EntityPlayerMP player) {
		for(ExtraRun run:this.finishRuns)
			run.run(player, null);
	}

	@Override
	public boolean canFinish(EntityPlayerMP player) {
		if(this.finishRequires.isEmpty())
			return true;
		up:for(List<Requirement> requires:this.finishRequires.values()) {
			for(Requirement require:requires)
				if(!require.apply(player, null))
					continue up;
			return true;
		}
		return false;
	}

	@Override
	public void addRun(ExtraRun add, Object... extraParams) {
		if(extraParams.length!=1||!(extraParams[0] instanceof String))
			return;
		String runTime = (String) extraParams[0];
		if(runTime.equalsIgnoreCase("start"))
			this.startRuns.add(add);
		else if(runTime.equalsIgnoreCase("finish"))
			this.finishRuns.add(add);
	}

	@Override
	public void addRequire(Requirement add, Object... extraParams) {
		if(extraParams.length!=1||!(extraParams[0] instanceof String))
			return;
		String group = (String) extraParams[0];
		if(!this.finishRequires.containsKey(group))
			this.finishRequires.put(group, Lists.newLinkedList());
		this.finishRequires.get(group).add(add);
	}
	
	public boolean isEmpty() {
		return this.taskID==null;
	}

}
