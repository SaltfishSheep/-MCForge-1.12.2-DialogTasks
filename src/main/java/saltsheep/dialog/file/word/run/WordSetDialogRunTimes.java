package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordSetDialogRunTimes extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "setDialogRunTimes";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"dialogID","value"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String dialogID = params.get("dialogID");
		int value = Integer.valueOf(params.get("value"));
		return new SetDialogRunTimes(dialogID,value);
	}
	
	public static class SetDialogRunTimes implements ExtraRun{
		private final String dialogID;
		private final int value;
		public SetDialogRunTimes(String dialogID,int value) {this.dialogID = dialogID;this.value = value;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ADialogChainBase tempChain = FDialogHandler.createDialog(dialogID, player);
			if(tempChain==null)
				return false;
			tempChain.setRunTimes(value);
			return true;
		}
	}

}
