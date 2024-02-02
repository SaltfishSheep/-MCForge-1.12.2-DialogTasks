package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordNotInDialog extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "notInDialog";
	}

	@Override
	public String[] paramWords() {
		return new String[] {};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		return new NotInDialog();
	}
	
	public static class NotInDialog implements Requirement{
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			return FDialogHandler.playersRunningDialog.get(player.getName())==null;
		}
	}

}
