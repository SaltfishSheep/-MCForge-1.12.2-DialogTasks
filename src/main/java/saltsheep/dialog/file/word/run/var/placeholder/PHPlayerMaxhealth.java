package saltsheep.dialog.file.word.run.var.placeholder;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler.Placeholder;

public class PHPlayerMaxhealth implements Placeholder {

	@Override
	public String nameRegex() {
		return "PLAYER_MAX_HEALTH";
	}

	@Override
	public String replace(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
		String[] bereplaces = Placeholder.getUseful(exp,this.nameRegex());
		for(String bereplace:bereplaces) {
			double count = player.getMaxHealth();
			exp = exp.replaceAll(new StringBuilder("%").append(bereplace).append("%").toString(), String.valueOf(count));
		}
		return exp;
	}

}
