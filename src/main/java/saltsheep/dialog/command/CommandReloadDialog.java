package saltsheep.dialog.command;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.base.FDialogHandler;

public class CommandReloadDialog extends CommandBase {

	@Override
	public String getName() {
		return "reloadDialog";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "§c/reloadDialog";
	}
	
	public int getRequiredPermissionLevel()
    {
        return 2;
    }

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		FDialogHandler.resetRunning();
		DialogTasks.unloadHandler();
		DialogTasks.loadHandler(server);
		sender.sendMessage(new TextComponentTranslation("command.message.reload"));
	}
	public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos){
		return super.getTabCompletions(server, sender, args, targetPos);
	}

}
