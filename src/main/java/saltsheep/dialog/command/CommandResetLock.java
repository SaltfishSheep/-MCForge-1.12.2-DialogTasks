package saltsheep.dialog.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import saltsheep.dialog.network.DataPlayerLock;

public class CommandResetLock extends CommandBase{

	@Override
	public String getName() {
		return "resetLock";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "§c/resetLock";
	}
	
	@Override
	public int getRequiredPermissionLevel(){
        return 0;
    }
	
	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender.getCommandSenderEntity()!=null&&sender.getCommandSenderEntity() instanceof EntityPlayerMP)
			DataPlayerLock.unlockByServer((EntityPlayerMP) sender.getCommandSenderEntity());
	}

}
