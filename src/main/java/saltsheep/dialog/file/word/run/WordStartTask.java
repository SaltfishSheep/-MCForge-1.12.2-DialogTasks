package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordStartTask extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "startTask";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"taskID"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String taskID = params.get("taskID");
		return new StartTask(taskID);
	}
	
	public static class StartTask implements ExtraRun{
		private final String taskID;
		public StartTask(String taskID) {this.taskID = taskID;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ATask task = FTaskHandler.getTask(this.taskID);
			if(task==null)
				return false;
			task.start(player);
			return true;
		}
	}

}
