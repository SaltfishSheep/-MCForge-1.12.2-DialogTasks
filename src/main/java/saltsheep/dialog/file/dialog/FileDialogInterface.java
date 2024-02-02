package saltsheep.dialog.file.dialog;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogInterface;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.base.IDialogCreator;

public class FileDialogInterface extends FDialogInterface {
	
	private final String nextDialogID;
	
	public FileDialogInterface(String text, String nextDialogID) {
		super(text, null);
		this.nextDialogID = nextDialogID;
	}
	
	@Override
	public ADialogChainBase createChain(EntityPlayerMP player) {
		return FDialogHandler.createDialog(this.nextDialogID, player);
	}
	
	public ADialogChainBase createChain(EntityPlayerMP player,EntityLivingBase hostingEntity) {
		IDialogCreator creator = FDialogHandler.getCreator(this.nextDialogID);
		if(creator instanceof FileDialogCreator)
			return ((FileDialogCreator)creator).create(player,hostingEntity);
		else
			return creator.create(player);
	}

}
