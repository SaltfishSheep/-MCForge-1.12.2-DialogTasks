package saltsheep.dialog.linkage;

public class LinkageHandler {
	
	public static boolean CNPC = false;
	public static boolean SHEEPHELPER = false;

	public static void handle() {
		if(hasClass("noppes.npcs.CustomNpcs")) {
			saltsheep.dialog.linkage.cnpc.Initer.init();
			CNPC = true;
		}
		if(hasClass("saltsheep.sheephelper.SheepHelper"))
			SHEEPHELPER = true;
	}
	
	public static boolean hasClass(String className){
		try {
			Thread.currentThread().getContextClassLoader().loadClass(className);
			return true;
		} catch (ClassNotFoundException e) {
			return false;
		}
	}
	
}
