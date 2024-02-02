package saltsheep.dialog.linkage.cnpc;

import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.linkage.cnpc.require.*;
import saltsheep.dialog.linkage.cnpc.run.*;

public class Initer {

	public static void init() {
		DialogTasks.info("Register the CNPC module.");
		saltsheep.dialog.base.Initer.registerAuto(new WordCanStartCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordFinishedCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStartingCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordGivePrizeCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordMarkFinishedCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStartCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordStopCNPCQuest());
		saltsheep.dialog.base.Initer.registerAuto(new WordOpenCNPCHire());
		saltsheep.dialog.base.Initer.registerAuto(new WordOpenCNPCShop());
		saltsheep.dialog.base.Initer.registerAuto(new WordOpenCNPCTransfer());
		saltsheep.dialog.base.Initer.registerAuto(new WordRunCNPCScript());
	}
	
}
