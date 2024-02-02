package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordTaskStartTimesInRange extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "taskStartTimesInRange";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"taskID","max","min"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		int max = params.containsKey("max")? Integer.valueOf(params.get("max")):Integer.MAX_VALUE;
		int min = params.containsKey("min")? Integer.valueOf(params.get("min")):Integer.MIN_VALUE;
		String taskID = params.get("taskID");
		return new TaskStartTimesInRange(taskID,max,min);
	}
	
	public static class TaskStartTimesInRange implements Requirement{
		
		private final String taskID;
		private final int max,min;
		public TaskStartTimesInRange(String taskID,int max,int min) {
			this.taskID = taskID;
			this.max = max;
			this.min = min;
		}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ATask task = FTaskHandler.getTask(taskID);
			if(task==null)
				return false;
			int times = task.getStartTimes(player);
			return times>=min&&times<=max;
		}
	}

}
