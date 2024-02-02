package saltsheep.dialog.file.word.run;

import java.util.List;
import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;

public class WordRemoveItem extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "removeItem";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"itemName","count"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String itemName = params.get("itemName");
		int count = params.containsKey("count")? Integer.valueOf(params.get("count")):1;
		return new RemoveItem(itemName,count);
	}
	
	public static class RemoveItem implements ExtraRun{
		private final String itemName;
		private final int count;
		public RemoveItem(String itemName, int count) {
			this.itemName = itemName;
			this.count = count;
		}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			List<Slot> slots = player.inventoryContainer.inventorySlots;
			int restCount = this.count;
			for(Slot slot:slots) {
				ItemStack item = slot.getStack();
				if(item.isEmpty()||!item.getDisplayName().equals(itemName))
					continue;
				if(item.getCount()>=restCount) {
					item.setCount(item.getCount()-restCount);
					restCount = 0;
				}else {
					restCount -= item.getCount();
					item.setCount(0);
				}
				if(restCount<=0)
					break;
			}
			return restCount<=0;
		}
	}

}
