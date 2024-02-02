package saltsheep.dialog.linkage.cnpc.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.wrapper.PlayerWrapper;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordCanStartCNPCQuest extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "canStartCNPCQuest";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"questID"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		int questID = Integer.valueOf(params.get("questID"));
		return new CanStartCNPCQuest(questID);
	}
	
	public static class CanStartCNPCQuest implements Requirement{
		private final int questID;
		public CanStartCNPCQuest(int questID) {this.questID=questID;}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			PlayerWrapper<?> playerPW = (PlayerWrapper<?>) noppes.npcs.api.wrapper.WrapperNpcAPI.Instance().getIEntity(player);
			return playerPW.canQuestBeAccepted(this.questID);
		}
	}

}
