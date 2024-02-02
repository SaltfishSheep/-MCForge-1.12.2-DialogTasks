package saltsheep.dialog.base;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayerMP;

public abstract class ADialogChainMO extends ADialogChainOptionsBase {

	protected List<ADialogChainBase> runnableChains = Lists.newLinkedList();
	
	public ADialogChainMO(EntityPlayerMP player) {
		super(player);
	}

	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		List<String> texts = Lists.newLinkedList();
		for(int i = 0;i<this.chainsInterfaceHook.size()&&runnableChains.size()<9;i++) {
			ADialogChainBase runnableNext = this.chainsInterfaceHook.get(i).createChain(this.player);
			if(runnableNext.canRun()) {
				this.runnableChains.add(runnableNext);
				texts.add("§e"+this.runnableChains.size()+" §r"+this.chainsInterfaceHook.get(i).getText(this.player));
			}
		}
		return texts.toArray(new String[texts.size()]);
	}
	
	@Override
	public void reply(int key) {
		if(this.runnableChains!=null&&!this.runnableChains.isEmpty())
			if(key>=2&&key<2+this.runnableChains.size())
				this.runnableChains.get(key-2).runChain();
	}

}
