package saltsheep.dialog.linkage.cnpc.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.common.util.EnumHelper;
import noppes.npcs.api.entity.IEntity;
import noppes.npcs.api.entity.IPlayer;
import noppes.npcs.api.event.PlayerEvent;
import noppes.npcs.api.wrapper.PlayerWrapper;
import noppes.npcs.api.wrapper.WrapperNpcAPI;
import noppes.npcs.constants.EnumScriptType;
import noppes.npcs.controllers.data.PlayerData;
import noppes.npcs.controllers.data.PlayerScriptData;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordRunCNPCScript extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "runCNPCScript";
	}

	@Override
	public String[] paramWords() {
		return new String[] {};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		return new RunCNPCScript();
	}
	
	public static class RunCNPCScript implements ExtraRun{
		public RunCNPCScript() {}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			PlayerWrapper<?> playerPW = (PlayerWrapper<?>) noppes.npcs.api.wrapper.WrapperNpcAPI.Instance().getIEntity(player);
			IEntity<?> he = hostingEntity==null? null:noppes.npcs.api.wrapper.WrapperNpcAPI.Instance().getIEntity(hostingEntity);
			PlayerScriptData handler = PlayerData.get(player).scriptData;
			DialogTasksEvent event = new DialogTasksEvent(playerPW,he);
			handler.runScript(DIALOG_TASKS, event);
			return WrapperNpcAPI.EVENT_BUS.post(event);
		}
	}
	
	public static final EnumScriptType DIALOG_TASKS;
	
	public static class DialogTasksEvent extends PlayerEvent{
		public final IEntity<?> hostingEntity;
		public DialogTasksEvent(IPlayer<?> player, IEntity<?> hostingEntity) {
			super(player);
			this.hostingEntity = hostingEntity;
		}
	}
	
	static {
		DIALOG_TASKS = EnumHelper.addEnum(EnumScriptType.class, "DIALOG_TASKS", new Class[]{String.class}, "dialogTasks");
	}

}
