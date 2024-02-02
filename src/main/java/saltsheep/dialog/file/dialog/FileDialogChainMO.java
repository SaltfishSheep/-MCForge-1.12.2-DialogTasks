package saltsheep.dialog.file.dialog;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogInterface;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.IRunAddable;

public class FileDialogChainMO extends AFileDialogChainOptionsBase {
	
	protected List<ADialogChainBase> runnableChains = Lists.newLinkedList();
	
	public FileDialogChainMO(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity,String dialogID,String[] dialogTexts,long[] dialogTimes,List<FileDialogInterface> nextChains,Map<String, List<Requirement>> requires,List<IRunAddable.ExtraRun>[] runs) {
		super(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
	}
	
	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		List<String> texts = Lists.newLinkedList();
		for(int i = 0;i<this.chainsInterfaceHook.size()&&this.runnableChains.size()<9;i++) {
			FDialogInterface inter = this.chainsInterfaceHook.get(i);
			ADialogChainBase runnableNext = null;
			if(inter instanceof FileDialogInterface)
				runnableNext = ((FileDialogInterface) inter).createChain(player, hostingEntity);
			else
				runnableNext = inter.createChain(this.player);
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
