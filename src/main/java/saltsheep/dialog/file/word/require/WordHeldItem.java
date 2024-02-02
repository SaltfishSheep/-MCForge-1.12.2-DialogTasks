package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordHeldItem extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "heldItem";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"itemName","count"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		String itemName = params.get("itemName");
		int count = params.containsKey("count")? Integer.valueOf(params.get("count")):1;
		return new HeldItem(itemName, count);
	}
	
	public static class HeldItem implements Requirement{
		private final String itemName;
		private final int count;
		public HeldItem(String itemName, int count) {this.itemName = itemName;this.count=count;}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			ItemStack item = player.getHeldItemMainhand();
			return item.getDisplayName().equals(itemName)&&item.getCount()>=this.count;
		}
	}

}
