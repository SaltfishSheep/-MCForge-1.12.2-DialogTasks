package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.network.DataPlayerLock;

public abstract class ADialogChainNONL extends ADialogChainNO {

	public ADialogChainNONL(EntityPlayerMP player) {
		super(player);
	}

	protected void lockPlayer() {
		DataPlayerLock.unlockByServer((EntityPlayerMP) this.player);
		FDialogHandler.playersRunningDialog.put(this.player.getName(), this);
	}

}
