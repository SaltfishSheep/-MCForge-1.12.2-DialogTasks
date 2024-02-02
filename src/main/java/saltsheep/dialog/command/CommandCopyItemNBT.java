package saltsheep.dialog.command;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;
import saltsheep.dialog.network.DataClipboard;

public class CommandCopyItemNBT extends CommandBase {

	@Override
	public String getName() {
		return "copyItemNBT";
	}

	@Override
	public String getUsage(ICommandSender sender) {
		return "usage.command.copyItemNBT";
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
		if(sender!=sender.getCommandSenderEntity())
			sender.sendMessage(new TextComponentTranslation("command.message.mustBySender"));
		if(!(sender instanceof EntityPlayerMP))
			sender.sendMessage(new TextComponentTranslation("command.message.mustByPlayer"));
		EntityPlayerMP player = (EntityPlayerMP) sender;
		DataClipboard.sendByServer(player, player.getHeldItemMainhand().serializeNBT().toString());
		sender.sendMessage(new TextComponentTranslation("command.message.alreadyCopyItemNBT"));
	}

}
