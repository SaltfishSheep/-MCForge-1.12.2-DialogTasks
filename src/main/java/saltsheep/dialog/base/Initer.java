package saltsheep.dialog.base;

import net.minecraft.util.text.ChatType;
import net.minecraftforge.common.util.EnumHelper;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.file.word.AFileWordRequireHandler;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.dialog.file.word.FFileWordCommonHandlerManager;
import saltsheep.dialog.file.word.IFileWordHandler;
import saltsheep.dialog.file.word.dialog.*;
import saltsheep.dialog.file.word.task.*;
import saltsheep.dialog.file.word.require.*;
import saltsheep.dialog.file.word.run.*;
import saltsheep.dialog.file.word.run.var.*;
import saltsheep.dialog.file.word.run.var.placeholder.*;

public class Initer {
	
	public static ChatType DIALOG = null;

	public static void init() {
		DIALOG = EnumHelper.addEnum(ChatType.class, "DIALOG", new Class<?>[] {byte.class}, (byte)-1);
		registerDialogs();
		registerTasks();
		registerWordDialog();
		registerWordTask();
		registerWordRequire();
		registerWordRun();
		registerVarPlaceholder();
	}
	
	private static void registerVarPlaceholder() {
		PlaceholderHandler.register(new PHDialogRun());
		PlaceholderHandler.register(new PHPlayerHealth());
		PlaceholderHandler.register(new PHPlayerMaxhealth());
		PlaceholderHandler.register(new PHTaskFinish());
		PlaceholderHandler.register(new PHTaskStart());
		PlaceholderHandler.register(new PHVarInt());
		PlaceholderHandler.register(new PHVar());
		PlaceholderHandler.register(new PHGlobalVarInt());
		PlaceholderHandler.register(new PHGlobalVar());
	}

	private static void registerDialogs() {
		//*FDialogHandler.register("调试",saltsheep.dialog.oldtest.Testing1::new,true);
	}
	
	private static void registerTasks() {
		//*FTaskHandler.register(task);
	}
	
	private static void registerWordDialog() {
		registerAuto(new saltsheep.dialog.file.word.dialog.WordSetID());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordSetType());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordSetMain());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordSetTextLine());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordSetText());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordAddNext());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordAddRequire());
		registerAuto(new saltsheep.dialog.file.word.dialog.WordAddRun());
	}
	
	private static void registerWordTask() {
		registerAuto(new saltsheep.dialog.file.word.task.WordSetID());
		registerAuto(new saltsheep.dialog.file.word.task.WordAddRequire());
		registerAuto(new saltsheep.dialog.file.word.task.WordAddRun());
	}
	
	private static void registerWordRun() {
		registerAuto(new WordSendMessage());
		registerAuto(new WordSetDialogRunTimes());
		registerAuto(new WordAddDialogRunTimes());
		registerAuto(new WordGiveItem());
		registerAuto(new WordGiveItemByNBT());
		registerAuto(new WordRemoveItem());
		registerAuto(new WordExecuteCommand());
		registerAuto(new WordStartTask());
		registerAuto(new WordStartTaskAllPlayers());
		registerAuto(new WordAddVar());
		registerAuto(new WordSetVar());
		registerAuto(new WordAssignVar());
	}

	private static void registerWordRequire() {
		registerAuto(new WordDialogRunTimesInRange());
		registerAuto(new WordNotInDialog());
		registerAuto(new WordTaskStartTimesInRange());
		registerAuto(new WordTaskStarting());
		registerAuto(new WordTaskFinishTimesInRange());
		registerAuto(new WordVarInRange());
		registerAuto(new WordHasItem());
		registerAuto(new WordHeldItem());
	}
	
	public static void registerAuto(IFileWordHandler word) {
		if(word instanceof AFileWordDialogHandler)
			FFileWordDialogHandlerManager.register((AFileWordDialogHandler) word);
		else if(word instanceof AFileWordTaskHandler)
			FFileWordTaskHandlerManager.register((AFileWordTaskHandler) word);
		else if(word instanceof AFileWordRequireHandler||word instanceof AFileWordRunHandler)
			FFileWordCommonHandlerManager.register(word);
		else return;
		DialogTasks.info("Register word handler successful,name:"+word.methodWord()+",type:"+word.handlerType());
	}

}
