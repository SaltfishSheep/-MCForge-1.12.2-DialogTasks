package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.player.PlayerHelper;

public class WordGiveItem extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "giveItem";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"item","count","damage","tagnbt"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		Item item = Item.getByNameOrId(params.get("item"));
		int count = params.containsKey("count")? Integer.valueOf(params.get("count")):1;
		int damage = params.containsKey("damage")? Integer.valueOf(params.get("damage")):0;
		String tagnbt = params.containsKey("tagnbt")? params.get("tagnbt"):"{}";
		return new GiveItem(item,count,damage,tagnbt);
	}
	
	public static class GiveItem implements ExtraRun{
		private final Item item;
		private final int count;
		private final int damage;
		private final String tagnbt;
		public GiveItem(Item item, int count, int damage, String tagnbt) {
			this.item = item;
			this.count = count;
			this.damage = damage;
			this.tagnbt = tagnbt;
		}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			String itemNBT = tagnbt.replaceAll("%PLAYER%", player.getName());
			try {
				PlayerHelper.giveOrDropItem(player, new ItemStack(item,count,damage,JsonToNBT.getTagFromJson(itemNBT)));
				return true;
			} catch (NBTException e) {
				DialogTasks.printError(e);
				return false;
			}
		}
	}

}
