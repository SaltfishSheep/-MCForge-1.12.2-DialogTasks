package saltsheep.dialog.event;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import saltsheep.dialog.DialogTasks;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.dialog.base.FTaskHandler;
import saltsheep.dialog.base.IDialogCreator;
import saltsheep.dialog.file.dialog.FileDialogCreator;

@EventBusSubscriber 
public class ServerWorking {

	@SubscribeEvent
	public static void onPlayerLoggedIn(PlayerLoggedInEvent event) {
		if(event.player.world.isRemote)
			return;
		FTaskHandler.checkTasksBeforeRunning((EntityPlayerMP) event.player);
	}
	
	@SubscribeEvent
	public static void onPlayerTick(PlayerTickEvent event) {
		if(event.player.world.isRemote||event.phase==TickEvent.Phase.END)
			return;
		if(event.player.world.getTotalWorldTime()%5==0)
			FTaskHandler.checkTasksRunning((EntityPlayerMP) event.player);
	}
	
	@SubscribeEvent
	public static void onPlayerInteract(PlayerInteractEvent.EntityInteract event) {
		if(event.getSide()!=Side.SERVER)
			return;
		Entity target = event.getTarget();
		if(target==null)
			return;
		if(DialogTasks.getFileHandler().entityDialogs.containsKey(target.getName())&&FDialogHandler.playersRunningDialog.get(event.getEntityPlayer().getName())==null) {
			for(String dialog:DialogTasks.getFileHandler().entityDialogs.get(target.getName())) {
				IDialogCreator creator = FDialogHandler.getCreator(dialog);
				ADialogChainBase chain = null;
				if(creator instanceof FileDialogCreator)
					chain = ((FileDialogCreator)creator).create(((EntityPlayerMP)event.getEntityPlayer()),(EntityLivingBase) event.getTarget());
				else
					chain = creator.create((EntityPlayerMP) event.getEntityPlayer());
				if(chain.canRun()) {
					chain.runChain();
					event.setCanceled(true);
					break;
				}
			}
		}
	}
	
}
