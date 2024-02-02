package saltsheep.dialog.base;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;

public class FTaskHandler {
	
	private static Map<String,ATask> tasks =Maps.newHashMap();

	private static Map<String,List<ATask>> playerRunningTasks = Maps.newHashMap();
	
	public static boolean register(ATask task) {
		if(tasks.containsKey(task.getID()))
			return false;
		tasks.put(task.getID(), task);
		return true;
	}
	
	public static void unregister(List<String> removes) {
		for(String remove:removes) {
			ATask task = tasks.get(remove);
			tasks.remove(remove);
			for(List<ATask> each:playerRunningTasks.values())
				each.remove(task);
		}
	}
	
	public static boolean has(String taskID) {
		return tasks.containsKey(taskID);
	}

	static void checkList(EntityPlayerMP player) {
		if(!playerRunningTasks.containsKey(player.getName()))
			playerRunningTasks.put(player.getName(), Lists.newArrayList());
	}

	public static void checkTasksBeforeRunning(EntityPlayerMP player) {
		checkList(player);
		NBTTagCompound nbt = DataManager.getData(player, false).getOrCreateCompound("tasks").getMCNBT();
		for(String eachTask:nbt.getKeySet()) {
			if((!tasks.containsKey(eachTask))||tasks.get(eachTask)==null)
				continue;
			NBTTagCompound task = nbt.getCompoundTag(eachTask);
			int startTimes = task.getInteger("startTimes");
			int finishTimes = task.getInteger("finishTimes");
			long lastStartTime = task.getLong("lastStartTime");
			long lastFinishTime = task.getLong("lastFinishTime");
			if(startTimes>finishTimes&&lastStartTime>=lastFinishTime) {
				addRunning(player, tasks.get(eachTask));
			}
		}
	}
	
	public static void checkTasksRunning(EntityPlayerMP player) {
		checkList(player);
		List<ATask> runningTasks = playerRunningTasks.get(player.getName());
		for(int i=runningTasks.size()-1;i>=0;i--) {
			runningTasks.get(i).checkFinish(player);
		}
	}
	
	public static boolean addRunning(EntityPlayerMP player, ATask task) {
		checkList(player);
		List<ATask> runningTasks = playerRunningTasks.get(player.getName());
		if(runningTasks.contains(task))
			return false;
		runningTasks.add(task);
		return true;
	}
	
	public static boolean removeRunning(EntityPlayerMP player, ATask task) {
		checkList(player);
		List<ATask> runningTasks = playerRunningTasks.get(player.getName());
		if(!runningTasks.contains(task))
			return false;
		runningTasks.remove(task);
		return true;
	}
	
	public static boolean isRunning(EntityPlayerMP player,ATask task) {
		checkList(player);
		List<ATask> runningTasks = playerRunningTasks.get(player.getName());
		if(!runningTasks.contains(task))
			return false;
		return true;
	}
	
	@Nullable
	public static ATask getTask(String taskID) {
		return tasks.get(taskID);
	}
	
	public static List<String> getTasks(){
		return Lists.newArrayList(tasks.keySet());
	}

}
