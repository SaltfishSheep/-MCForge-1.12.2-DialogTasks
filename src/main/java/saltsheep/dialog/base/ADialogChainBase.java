package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.network.DataPlayerLock;

public abstract class ADialogChainBase {

	public EntityPlayerMP player;
	
	//*Use for map
	public ADialogChainBase(EntityPlayerMP player) {
		this.player = player;
	}
	
	protected abstract String getID();
	
	protected abstract String[] getDialogTexts();
	
	protected String[] getDialogTextsPlayer() {
		String[] texts = this.getDialogTexts();
		String[] result = new String[texts.length];
		for(int i=0;i<texts.length;i++)
			result[i] = texts[i].replaceAll("%PLAYER%", this.player.getName());
		return result;
	}
	
	protected abstract long[] getDialogTimes();
	
	protected abstract void runExtra(int point);
	
	public boolean canRun() {
		return true;
	}
	
	//*这个方法必须在runChain尾部调用
	protected void countRunTimes() {
		this.setRunTimes(this.getRunTimes()+1);
	}
	
	public void setRunTimes(int times) {
		DataManager.getData(player, false).getOrCreateCompound("dialogRunCount").getMCNBT().setInteger(this.getID(), (int)times);
	}
	
	public int getRunTimes() {
		return DataManager.getData(player, false).getOrCreateCompound("dialogRunCount").getInteger(this.getID());
	}
	
	protected void lockPlayer() {
		DataPlayerLock.lockByServer((EntityPlayerMP) this.player);
		FDialogHandler.playersRunningDialog.put(this.player.getName(), this);
	}
	
	protected void unLockPlayer() {
		DataPlayerLock.unlockByServer((EntityPlayerMP) this.player);
		FDialogHandler.playersRunningDialog.put(this.player.getName(), null);
	}
	
	public abstract void runChain();
	
}
