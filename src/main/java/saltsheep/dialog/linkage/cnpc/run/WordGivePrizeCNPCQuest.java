package saltsheep.dialog.linkage.cnpc.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.api.handler.data.IQuest;
import noppes.npcs.api.item.IItemStack;
import noppes.npcs.api.wrapper.WrapperNpcAPI;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.player.PlayerHelper;

public class WordGivePrizeCNPCQuest extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "givePrizeCNPCQuest";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"questID"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		int questID = Integer.valueOf(params.get("questID"));
		return new GivePrizeCNPCQuest(questID);
	}
	
	public static class GivePrizeCNPCQuest implements ExtraRun{
		private final int questID;
		public GivePrizeCNPCQuest(int questID) {this.questID = questID;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			IQuest quest = WrapperNpcAPI.Instance().getQuests().get(questID);
			if(quest==null)
				return false;
			IItemStack[] items = quest.getRewards().getItems();
			if(items==null||items.length==0)
				return true;
			for(IItemStack item:items)
				PlayerHelper.giveOrDropItem(player, item.getMCItemStack().copy());
			return true;
		}
	}

}
