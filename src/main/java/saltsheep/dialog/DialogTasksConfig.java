package saltsheep.dialog;

import net.minecraftforge.common.config.Config;

@Config(modid = DialogTasks.MODID)
@Config.LangKey("config."+DialogTasks.MODID+"general")
public class DialogTasksConfig {

	public static boolean isCacheInDialogLock = true;
	
	public static boolean isReadInSave = true;
	
}
