package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;

//*Just sure
public abstract class ADialogChainJS extends ADialogChainOptionsBase {
	
	protected int usefulChoose = -1;

	public ADialogChainJS(EntityPlayerMP player) {
		super(player);
	}

	@Override
	public void reply(int key) {
		if(this.usefulChoose!=-1)
			this.chainsInterfaceHook.get(usefulChoose).createChain(this.player).runChain();
	}
	
	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		for(int i=0;i<this.chainsInterfaceHook.size();i++) {
			if(this.chainsInterfaceHook.get(i).createChain(this.player).canRun()) {
				usefulChoose = i;
				break;
			}
		}
		return (usefulChoose!=-1)? new String[]{"§eANY §r"+this.chainsInterfaceHook.get(usefulChoose).getText(this.player)}:null;
	}

}
