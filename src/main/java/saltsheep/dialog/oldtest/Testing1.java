package saltsheep.dialog.oldtest;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainJS;
import saltsheep.dialog.base.FDialogInterface;
import saltsheep.dialog.base.FDialogHandler;

public class Testing1 extends ADialogChainJS {

	public Testing1(EntityPlayerMP player) {
		super(player);
	}

	@Override
	protected void initChainsInterfaceHooks() {
		this.chainsInterfaceHook = new ArrayList<FDialogInterface>();
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认JS",Testing2::new));
	}

	@Override
	protected String getID() {
		return "调试";
	}

	@Override
	protected String[] getDialogTexts() {
		return new String[]{"%PLAYER% : 测试对话中","听得清吗？","接下来进行JS(Just Sure)测试"};
	}

	@Override
	protected long[] getDialogTimes() {
		return new long[] {1000,1000,1000};
	}

	@Override
	protected void runExtra(int point) {
	}

	@Override
	public boolean canRun() {
		return FDialogHandler.playersRunningDialog.get(this.player.getName())==null;
	}

}
