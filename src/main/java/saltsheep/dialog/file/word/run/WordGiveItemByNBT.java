package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.player.PlayerHelper;

public class WordGiveItemByNBT extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "giveItemByNBT";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"nbt"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String nbt = params.get("nbt");
		return new GiveItemByNBT(nbt);
	}
	
	public static class GiveItemByNBT implements ExtraRun{
		private final String nbt;
		public GiveItemByNBT(String nbt) {this.nbt = nbt;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			String itemNBT = nbt.replaceAll("%PLAYER%", player.getName());
			try {
				PlayerHelper.giveOrDropItem(player, new ItemStack(JsonToNBT.getTagFromJson(itemNBT)));
				return true;
			} catch (NBTException e) {
				DialogTasks.printError(e);
				return false;
			}
		}
	}

}
