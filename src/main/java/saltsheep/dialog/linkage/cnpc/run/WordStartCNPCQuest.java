package saltsheep.dialog.linkage.cnpc.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.wrapper.PlayerWrapper;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordStartCNPCQuest extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "startCNPCQuest";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"questID"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		int questID = Integer.valueOf(params.get("questID"));
		return new StartCNPCQuest(questID);
	}
	
	public static class StartCNPCQuest implements ExtraRun{
		private final int questID;
		public StartCNPCQuest(int questID) {this.questID = questID;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			PlayerWrapper<?> playerPW = (PlayerWrapper<?>) noppes.npcs.api.wrapper.WrapperNpcAPI.Instance().getIEntity(player);
			playerPW.startQuest(this.questID);
			return true;
		}
	}

}
