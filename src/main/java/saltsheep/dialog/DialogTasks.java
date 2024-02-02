package saltsheep.dialog;

import org.apache.logging.log4j.Logger;

import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import net.minecraftforge.fml.common.event.FMLServerStoppingEvent;
import saltsheep.dialog.base.Initer;
import saltsheep.dialog.command.IniterCommand;
import saltsheep.dialog.file.FFileHandler;
import saltsheep.dialog.linkage.LinkageHandler;
import saltsheep.dialog.network.NetworkRegistryHandler;

@Mod(modid = DialogTasks.MODID, name = DialogTasks.NAME, version = DialogTasks.VERSION, acceptableRemoteVersions="*")
public class DialogTasks
{
    public static final String MODID = "dialogtasks";
    public static final String NAME = "DialogTasks";
    public static final String VERSION = "1.4";
    public static DialogTasks instance;

    private static Logger logger;
    
    private static FFileHandler handler;

    public DialogTasks() {
    	instance = this;
    }
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event){
        logger = event.getModLog();
        instance = this;
    }

    @EventHandler
    public void init(FMLInitializationEvent event){
    	NetworkRegistryHandler.register();
        Initer.init();
        LinkageHandler.handle();
    }
    
    @EventHandler
    public static void onServerStarting(FMLServerStartingEvent event){
    	IniterCommand.init(event);
    	DialogTasks.loadHandler(event.getServer());
	}
    
    @EventHandler
    public static void onServerStopping(FMLServerStoppingEvent event) {
    	unloadHandler();
    }
    
    public static void loadHandler(MinecraftServer server) {
    	handler = FFileHandler.loadHandler(server);
    }
    
    public static void unloadHandler() {
    	FFileHandler.unloadHandler(getFileHandler());
    }
    
    public static Logger getLogger() {
    	return logger;
    }
    
    public static MinecraftServer getMCServer() {
    	return FMLCommonHandler.instance().getMinecraftServerInstance();
    }
    
    public static void printError(Throwable error) {
    	StringBuilder messages = new StringBuilder();
    	for(StackTraceElement stackTrace : error.getStackTrace()) {
    		messages.append("\n").append(stackTrace.toString());
		}
    	logger.error("Warning!The mod of me(Saltfish_Sheep) meet an error:\n"+"Error Type:"+error.getClass()+"-"+error.getMessage()+"\n"+messages);
    }
    
    public static void info(String str) {
    	logger.info(str);
    }
    
    public static void info(Object obj) {
    	if(obj == null)
    		logger.info("null has such obj.");
    	else
    		logger.info(obj.toString());
    }

	public static FFileHandler getFileHandler() {
		return handler;
	}
    
    
}
