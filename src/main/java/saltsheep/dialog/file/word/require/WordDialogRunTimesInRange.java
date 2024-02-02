package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordDialogRunTimesInRange extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "dialogRunTimesInRange";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"dialogID","max","min"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		int max = params.containsKey("max")? Integer.valueOf(params.get("max")):Integer.MAX_VALUE;
		int min = params.containsKey("min")? Integer.valueOf(params.get("min")):Integer.MIN_VALUE;
		String dialogID = params.get("dialogID");
		return new DialogRunTimesInRange(dialogID,max,min);
	}
	
	public static class DialogRunTimesInRange implements Requirement{
		
		private final String dialogID;
		private final int max,min;
		public DialogRunTimesInRange(String dialogID,int max,int min) {
			this.dialogID = dialogID;
			this.max = max;
			this.min = min;
		}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ADialogChainBase chain = FDialogHandler.createDialog(this.dialogID, player);
			if(chain==null)
				return false;
			int time = chain.getRunTimes();
			return time>=min&&time<=max;
		}
	}

}
