package saltsheep.dialog.file.word.run.var.placeholder;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler.Placeholder;

public class PHDialogRun implements Placeholder {

	@Override
	public String nameRegex() {
		return "DIALOG_RUN_.+";
	}

	@Override
	public String replace(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
		String[] bereplaces = Placeholder.getUseful(exp,this.nameRegex());
		for(String bereplace:bereplaces) {
			String dialogID = bereplace.substring(11);
			ADialogChainBase dialog = FDialogHandler.createDialog(dialogID, player);
			int count = dialog!=null? dialog.getRunTimes():0;
			exp = exp.replaceAll(new StringBuilder("%").append(bereplace).append("%").toString(), String.valueOf(count));
		}
		return exp;
	}

}
