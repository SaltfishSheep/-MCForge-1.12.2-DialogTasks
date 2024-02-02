package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler;

public class WordExecuteCommand extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "executeCommand";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"command"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String command = params.get("command");
		return new ExecuteCommand(command);
	}
	
	public static class ExecuteCommand implements ExtraRun{
		private final String command;
		public ExecuteCommand(String command) {this.command = command;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			String send = PlaceholderHandler.handler(player, hostingEntity, (command.replaceAll("%PLAYER%", player.getName())));
			DialogTasks.getMCServer().getCommandManager().executeCommand(DialogTasks.getMCServer(), send);
			return true;
		}
	}

}
