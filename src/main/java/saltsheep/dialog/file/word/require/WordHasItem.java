package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;

public class WordHasItem extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "hasItem";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"itemName","count"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		String itemName = params.get("itemName");
		int count = params.containsKey("count")? Integer.valueOf(params.get("count")):1;
		return new HasItem(itemName, count);
	}
	
	public static class HasItem implements Requirement{
		private final String itemName;
		private final int count;
		public HasItem(String itemName, int count) {this.itemName = itemName;this.count=count;}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			int meetCount = 0;
			boolean enough = false;
			for(ItemStack eachItem:player.inventoryContainer.getInventory()) {
				if(!eachItem.isEmpty()&&eachItem.getDisplayName().equals(this.itemName))
					meetCount += eachItem.getCount();
				if(meetCount>=count) {
					enough = true;
					break;
				}
			}
			return enough;
		}
	}

}
