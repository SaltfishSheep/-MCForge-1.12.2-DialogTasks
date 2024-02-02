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

public class WordOpenCNPCTransfer extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "openCNPCTransfer";
	}

	@Override
	public String[] paramWords() {
		return new String[] {};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		return new OpenCNPCTransfer();
	}
	
	public static class OpenCNPCTransfer implements ExtraRun{
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			System.out.println(0);
			if(hostingEntity==null||!(hostingEntity instanceof EntityNPCInterface))
				return false;
			System.out.println(1);
			EntityNPCInterface npc = (EntityNPCInterface)hostingEntity;
			if(npc.roleInterface.getType()!=RoleType.TRANSPORTER)
				return false;
			System.out.println(2);
			NoppesUtilServer.setEditingNpc(player, (EntityNPCInterface) hostingEntity);
			NoppesUtilServer.sendOpenGui(player, EnumGuiType.PlayerTransporter, npc);
			return true;
		}
	}

}
