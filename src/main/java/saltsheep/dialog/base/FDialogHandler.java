package saltsheep.dialog.base;

import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayerMP;

public class FDialogHandler {

	private static final HashMap<String,IDialogCreator> dialogs = new HashMap<String,IDialogCreator>();
	private static final HashMap<String,Boolean> dialogsMain = new HashMap<String,Boolean>();
	
	public static final HashMap<String,ADialogChainBase> playersRunningDialog = new HashMap<String,ADialogChainBase>();
	
	public static void register(String ID ,IDialogCreator creator,boolean isMain) {
		dialogs.put(ID, creator);
		dialogsMain.put(ID, isMain);
	}
	
	public static void unregister(List<String> removes) {
		for(String remove:removes) {
			dialogs.remove(remove);
			dialogsMain.remove(remove);
		}
	}
	
	public static boolean isMain(String ID) {
		if(!dialogsMain.containsKey(ID))
			return false;
		else
			return dialogsMain.get(ID);
	}
	
	public static List<String> getDialogsID() {
		return Lists.newArrayList(dialogs.keySet());
	}
	
	public static List<String> getMainDialogsID(){
		List<String> mains = Lists.newLinkedList();
		for(Entry<String, Boolean> each:dialogsMain.entrySet())
			if(each.getValue())
				mains.add(each.getKey());
		return mains;
	}
	
	public static void resetRunning() {
		playersRunningDialog.clear();
	}
	
	@Nullable
	public static IDialogCreator getCreator(String ID) {
		return dialogs.get(ID);
	}
	
	@Nullable
	public static ADialogChainBase createDialog(String ID,EntityPlayerMP playerIn) {
		if(dialogs.containsKey(ID)) 
			return dialogs.get(ID).create(playerIn);
		else 
			return null;
	}
	
	@Nullable
	public static ADialogChainBase createMainDialog(String ID,EntityPlayerMP playerIn) {
		if(dialogsMain.containsKey(ID))
			return createDialog(ID,playerIn);
		else
			return null;
	}
}
