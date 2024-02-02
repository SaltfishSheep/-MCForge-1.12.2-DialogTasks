package saltsheep.dialog.file.word.run.var.placeholder;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ATask;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler.Placeholder;

public class PHTaskFinish implements Placeholder {

	@Override
	public String nameRegex() {
		return "TAST_FINISH_.+";
	}

	@Override
	public String replace(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
		String[] bereplaces = Placeholder.getUseful(exp,this.nameRegex());
		for(String bereplace:bereplaces) {
			String taskID = bereplace.substring(12);
			ATask task = FTaskHandler.getTask(taskID);
			int count = task!=null? task.getFinishTimes(player):0;
			exp = exp.replaceAll(new StringBuilder("%").append(bereplace).append("%").toString(), String.valueOf(count));
		}
		return exp;
	}

}
