package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordTaskStarting extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "taskStarting";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"taskID"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		String taskID = params.get("taskID");
		return new TaskStarting(taskID);
	}
	
	public static class TaskStarting implements Requirement{
		
		private final String taskID;
		public TaskStarting(String taskID) {this.taskID = taskID;}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ATask task = FTaskHandler.getTask(taskID);
			if(task==null)
				return false;
			return task.isStarting(player);
		}
	}

}
