package saltsheep.dialog.oldtest;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainNONL;

public class TestingShouldntRun extends ADialogChainNONL {

	public TestingShouldntRun(EntityPlayerMP player) {
		super(player);
	}

	@Override
	protected String getID() {
		return "调试4";
	}

	@Override
	protected String[] getDialogTexts() {
		return new String[] {"退出锁定","调试结束！"};
	}

	@Override
	protected long[] getDialogTimes() {
		return new long[] {1000,1000};
	}

	@Override
	protected void runExtra(int point) {
	}

	@Override
	public boolean canRun() {
		return false;
	}

}
