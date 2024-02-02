package saltsheep.dialog.command;

import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import saltsheep.dialog.DialogTasks;

public class IniterCommand {
	
	public static void init(FMLServerStartingEvent event){
		try {
			event.registerServerCommand(new CommandCopyItemNBT());
			event.registerServerCommand(new CommandReloadDialog());
			event.registerServerCommand(new CommandRunChain());
			event.registerServerCommand(new CommandResetChainRun());
			event.registerServerCommand(new CommandResetLock());
			event.registerServerCommand(new CommandStartTask());
		}catch(Throwable error) {
			DialogTasks.printError(error);
		}
	}

}
