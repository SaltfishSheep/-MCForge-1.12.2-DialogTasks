package saltsheep.dialog.linkage.cnpc.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import noppes.npcs.NoppesUtilServer;
import noppes.npcs.api.constants.RoleType;
import noppes.npcs.constants.EnumGuiType;
import noppes.npcs.entity.EntityNPCInterface;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordOpenCNPCHire extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "openCNPCHire";
	}

	@Override
	public String[] paramWords() {
		return new String[] {};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		return new OpenCNPCHire();
	}
	
	public static class OpenCNPCHire implements ExtraRun{
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			if(hostingEntity==null||!(hostingEntity instanceof EntityNPCInterface))
				return false;
			EntityNPCInterface npc = (EntityNPCInterface)hostingEntity;
			if(npc.roleInterface.getType()!=RoleType.FOLLOWER)
				return false;
			NoppesUtilServer.setEditingNpc(player, (EntityNPCInterface) hostingEntity);
			NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerFollowerHire, npc);
			return true;
		}
	}

}
