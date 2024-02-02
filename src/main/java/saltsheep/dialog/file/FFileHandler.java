package saltsheep.dialog.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.server.MinecraftServer;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.DialogTasksConfig;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.file.FormatHelper.MethodFormat;
import saltsheep.dialog.file.dialog.FileDialogCreator;
import saltsheep.dialog.file.task.FileTask;
import saltsheep.dialog.file.word.dialog.FFileWordDialogHandlerManager;
import saltsheep.dialog.file.word.task.FFileWordTaskHandlerManager;

public class FFileHandler {
	
	public static String CODING = "UTF-8";

	private List<FileDialogCreator> dialogsIn = Lists.newArrayList();
	
	private List<FileTask> tasksIn = Lists.newArrayList();
	
	public Map<String,String[]> entityDialogs = Maps.newHashMap();
	
	public void readPacket(File packet){
		for(File each:packet.listFiles()) {
			if(each.isDirectory()) {
				this.readPacket(each);
				continue;
			}
			try {
				if(each.getName().toLowerCase().endsWith(".dialog")) {
					FileDialogCreator dialog = this.readDialog(each);
					if(dialog!=null&&!dialog.isEmpty()) {
						this.dialogsIn.add(dialog);
						DialogTasks.info("Register dialog successful,name:"+dialog.dialogID);
					}
				}else if(each.getName().toLowerCase().endsWith(".task")) {
					FileTask task = this.readTask(each);
					if(task!=null&&!task.isEmpty()) {
						this.tasksIn.add(task);
						DialogTasks.info("Register task successful,name:"+task.taskID);
					}
				}else if(each.getName().toLowerCase().endsWith(".entity")) {
					String entityName = each.getName().substring(0, each.getName().length()-7);
					String[] dialogs = this.readEntity(each);
					if(dialogs!=null&&dialogs.length>0) {
						this.entityDialogs.put(entityName, dialogs);
						DialogTasks.info("Mark hosting entity successful,entity name:"+entityName);
					}
				}
			}catch(Throwable e) {
				e.printStackTrace();
			}
		}
	}
	
	public FileDialogCreator readDialog(File dialog) throws IOException {	
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(dialog),CODING));
		FileDialogCreator creator = new FileDialogCreator();
		reader.lines().forEachOrdered((raw)->{
			raw = raw.replaceAll("#.*", "");
			if(!raw.isEmpty()) {
				MethodFormat format = FormatHelper.formatMethod(raw);
				FFileWordDialogHandlerManager.handlerMain(creator, format.methodWord, format.params);
			}
		});
		reader.close();
		return creator;
	}
	
	public FileTask readTask(File task) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(task),CODING));
		FileTask fileTask = new FileTask();
		reader.lines().forEachOrdered((raw)->{
			raw = raw.replaceAll("#.*", "");
			if(!raw.isEmpty()) {
				MethodFormat format = FormatHelper.formatMethod(raw);
				FFileWordTaskHandlerManager.handlerMain(fileTask, format.methodWord, format.params);
			}
		});
		reader.close();
		return fileTask;
	}
	
	public String[] readEntity(File entity) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(entity),CODING));
		List<String> dialogs = Lists.newLinkedList();
		reader.lines().forEachOrdered((raw)->{
			if(!raw.isEmpty()) {
				dialogs.add(raw);
			}
		});
		reader.close();
		return dialogs.toArray(new String[dialogs.size()]);	
	}
	
	//*Called when server starting.
	public static FFileHandler loadHandler(MinecraftServer server) {
		File dataPacket = DialogTasksConfig.isReadInSave?new File(server.getWorldIconFile().getParentFile(), "dialogtasks"):new File(".\\dialogtasks");;
		if(!dataPacket.exists())
			dataPacket.mkdirs();
		FFileHandler handler = new FFileHandler();
		handler.readPacket(dataPacket);
		for(FileDialogCreator dialog:handler.dialogsIn)
			FDialogHandler.register(dialog.dialogID, dialog, dialog.isMain);
		for(FileTask task:handler.tasksIn)
			FTaskHandler.register(task);
		server.getPlayerList().getPlayers().forEach((player)->FTaskHandler.checkTasksBeforeRunning(player));
		return handler;
	}
	
	//*Called when server stopping.
	public static void unloadHandler(FFileHandler handler) {
		List<String> ids =Lists.newLinkedList();
		for(FileDialogCreator each:handler.dialogsIn) {
			ids.add(each.dialogID);
			DialogTasks.info("Unload dialog successful,name:"+each.dialogID);
		}
		FDialogHandler.unregister(ids);
		ids.clear();
		for(FileTask each:handler.tasksIn) {
			ids.add(each.taskID);
			DialogTasks.info("Unload task successful,name:"+each.taskID);
		}
		FTaskHandler.unregister(ids);
	}
	
}
