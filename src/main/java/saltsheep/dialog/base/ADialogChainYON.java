package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;

//*Yes Or No
public abstract class ADialogChainYON extends ADialogChainOptionsBase {

	public ADialogChainYON(EntityPlayerMP player) {
		super(player);
	}

	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		return (this.chainsInterfaceHook!=null&&!this.chainsInterfaceHook.isEmpty())? new String[]{"§eY §r"+this.chainsInterfaceHook.get(0).getText(this.player),"§eN §r"+this.chainsInterfaceHook.get(1).getText(this.player)}:null;
	}
	
	@Override
	public void reply(int key) {
		if(this.chainsInterfaceHook != null) {
			if(key==21)//*Y
				this.chainsInterfaceHook.get(0).createChain(this.player).runChain();
			if(key==49)//*N
				this.chainsInterfaceHook.get(1).createChain(this.player).runChain();
		}
	}

}
