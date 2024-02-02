package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.linkage.LinkageHandler;
import saltsheep.lib.nbt.SheepNBT;
import saltsheep.lib.player.PlayerHelper;
import saltsheep.sheephelper.file.FileHandler;

public class DataManager {

	public static SheepNBT getData(EntityPlayerMP player, boolean isGlobal) {
		if(LinkageHandler.SHEEPHELPER) {
			if(isGlobal)
				return FileHandler.current.getData().getOrCreateCompound("DialogTasks");
			else
				return FileHandler.current.getPlayerData(player.getName()).getOrCreateCompound("DialogTasks");
		}
		if(isGlobal)
			return new SheepNBT(player.server.worlds[0].getWorldInfo().getDimensionData(0)).getOrCreateCompound("SaltSheepData").getOrCreateCompound("DialogTasks");
		else
			return PlayerHelper.getSheepData(player).getOrCreateCompound("DialogTasks");
	}
	
}
