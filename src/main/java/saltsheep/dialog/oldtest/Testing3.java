package saltsheep.dialog.oldtest;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainMO;
import saltsheep.dialog.base.FDialogInterface;

public class Testing3 extends ADialogChainMO {

	public Testing3(EntityPlayerMP player) {
		super(player);
	}
	
	@Override
	protected void initChainsInterfaceHooks() {
		this.chainsInterfaceHook = new ArrayList<FDialogInterface>();
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 不可运行",TestingShouldntRun::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
		this.chainsInterfaceHook.add(new FDialogInterface("%PLAYER% : 确认MO",Testing4::new));
	}

	@Override
	protected String getID() {
		return "调试3";
	}

	@Override
	protected String[] getDialogTexts() {
		return new String[] {"%PLAYER% : 调试3","请确认MO(More Options)"};
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
		return true;
	}

}
