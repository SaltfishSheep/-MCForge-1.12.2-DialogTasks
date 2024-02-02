package saltsheep.dialog.file.dialog;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.FDialogInterface;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.IRunAddable;

public class FileDialogChainJS extends AFileDialogChainOptionsBase {

	private int usefulChoose = -1;
	
	public FileDialogChainJS(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity,String dialogID,String[] dialogTexts,long[] dialogTimes,List<FileDialogInterface> nextChains,Map<String, List<Requirement>> requires,List<IRunAddable.ExtraRun>[] runs) {
		super(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
	}
	
	@Override
	public String[] getNextChainsTextsAndRemoveUnrunnableChains() {
		for(int i=0;i<this.chainsInterfaceHook.size();i++) {
			FDialogInterface inter = this.chainsInterfaceHook.get(i);
			if(inter instanceof FileDialogInterface&&((FileDialogInterface)inter).createChain(this.player,this.hostingEntity).canRun()) 
				usefulChoose = i;
			else if(inter.createChain(this.player).canRun()) 
				usefulChoose = i;
			if(usefulChoose==i)
				break;
		}
		return (usefulChoose!=-1)? new String[]{"§eANY §r"+this.chainsInterfaceHook.get(usefulChoose).getText(this.player)}:null;
	}
	
	@Override
	public void reply(int key) {
		if(this.usefulChoose!=-1) {
			if(this.chainsInterfaceHook.get(usefulChoose) instanceof FileDialogInterface)
				((FileDialogInterface)this.chainsInterfaceHook.get(usefulChoose)).createChain(this.player,this.hostingEntity).runChain();
			else
				this.chainsInterfaceHook.get(usefulChoose).createChain(this.player).runChain();
		}
	}

}
