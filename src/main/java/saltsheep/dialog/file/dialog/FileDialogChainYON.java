package saltsheep.dialog.file.dialog;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.FDialogInterface;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.IRunAddable;

public class FileDialogChainYON extends AFileDialogChainOptionsBase {
	
	public FileDialogChainYON(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity,String dialogID,String[] dialogTexts,long[] dialogTimes,List<FileDialogInterface> nextChains,Map<String, List<Requirement>> requires,List<IRunAddable.ExtraRun>[] runs) {
		super(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
	}
	
	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		return (this.chainsInterfaceHook!=null&&!this.chainsInterfaceHook.isEmpty())? new String[]{"§eY §r"+this.chainsInterfaceHook.get(0).getText(this.player),"§eN §r"+this.chainsInterfaceHook.get(1).getText(this.player)}:null;
	}
	
	@Override
	public void reply(int key) {
		if(this.chainsInterfaceHook != null) {
			FDialogInterface Y = this.chainsInterfaceHook.get(0);
			FDialogInterface N = this.chainsInterfaceHook.get(1);
			if(key==21) {
				if(Y instanceof FileDialogInterface)
					((FileDialogInterface) Y).createChain(player, hostingEntity).runChain();
				else
					Y.createChain(this.player).runChain();
			}else if(key==49) {
				if(N instanceof FileDialogInterface)
					((FileDialogInterface) N).createChain(player, hostingEntity).runChain();
				else
					N.createChain(this.player).runChain();
			}
		}
	}

}
