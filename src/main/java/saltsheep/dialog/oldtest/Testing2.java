package saltsheep.dialog.oldtest;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainYON;
import saltsheep.dialog.base.FDialogInterface;

public class Testing2 extends ADialogChainYON{

	public Testing2(EntityPlayerMP player) {
		super(player);
	}

	@Override
	protected void initChainsInterfaceHooks() {
		this.chainsInterfaceHook = new ArrayList<FDialogInterface>();
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认YON",Testing3::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认YON",Testing3::new));
	}

	@Override
	protected String getID() {
		return "调试2";
	}

	@Override
	protected String[] getDialogTexts() {
		return new String[] {"%PLAYER% : 调试2","请确认YON(Yes or No)效果"};
	}

	@Override
	protected long[] getDialogTimes() {
		return new long[]{1000,1000};
	}

	@Override
	protected void runExtra(int point) {
	}

	@Override
	public boolean canRun() {
		return true;
	}

}
