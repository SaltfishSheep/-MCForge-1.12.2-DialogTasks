package saltsheep.dialog.file.word.run.var.placeholder;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.DataManager;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler.Placeholder;

public class PHVar implements Placeholder {

	@Override
	public String nameRegex() {
		return "VAR_.+";
	}

	@Override
	public String replace(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
		String[] bereplaces = Placeholder.getUseful(exp,this.nameRegex());
		for(String bereplace:bereplaces) {
			String varName = bereplace.substring(4);
			double count = DataManager.getData(player, false).getOrCreateCompound("vars").getDouble(varName);
			exp = exp.replaceAll(new StringBuilder("%").append(bereplace).append("%").toString(), String.valueOf(count));
		}
		return exp;
	}

}
