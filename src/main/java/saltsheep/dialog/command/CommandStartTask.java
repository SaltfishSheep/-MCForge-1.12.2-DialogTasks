package saltsheep.dialog.command;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.base.FTaskHandler;

public class CommandStartTask extends CommandBase {

	@Override
	public String getName() {
		return "startTask";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "usage.command.startTask";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(args.length == 1) {
			if(sender.getCommandSenderEntity()!=null&&sender.getCommandSenderEntity() instanceof EntityPlayerMP ) {
				if(FTaskHandler.has(args[0])) {
					ATask task = FTaskHandler.getTask(args[0]);
					if(task.isStarting((EntityPlayerMP) sender.getCommandSenderEntity()))
						sender.sendMessage(new TextComponentTranslation("command.message.taskCantRun"));
					task.start((EntityPlayerMP) sender);
				}else sender.sendMessage(new TextComponentTranslation("command.message.noTask"));
			}
		}else{
			sender.sendMessage(new TextComponentTranslation(this.getUsage(sender)));
		}
	}
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos){
		if(args.length == 1)
			return FTaskHandler.getTasks();
		return super.getTabCompletions(server, sender, args, targetPos);
	}

}
