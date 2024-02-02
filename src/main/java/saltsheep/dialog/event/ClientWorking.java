package saltsheep.dialog.event;

import java.util.Collections;
import java.util.List;

import org.lwjgl.input.Keyboard;

import com.google.common.collect.Lists;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.MovementInput;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent.PlayerTickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import saltsheep.dialog.DialogTasksConfig;
import saltsheep.dialog.network.DataDialogReply;

@EventBusSubscriber
public class ClientWorking {
	
	public static boolean isLocked = false;

	//*当锁定玩家时，获取玩家按键
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void checkLock(KeyInputEvent event) {
		if(ClientWorking.isLocked) {
			if(Keyboard.getEventKeyState()) {
				DataDialogReply.sendPacketToServer(Minecraft.getMinecraft().player, Keyboard.getEventKey());
				KeyBinding.unPressAllKeys();
			}
		}
	}

	//*当锁定玩家时，取消任何移动
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void checkLock(InputUpdateEvent event) {
		if(ClientWorking.isLocked) {
			MovementInput input = event.getMovementInput();
			input.backKeyDown = false;
			input.forwardKeyDown = false;
			input.jump = false;
			input.leftKeyDown = false;
			input.moveForward = 0;
			input.moveStrafe = 0;
			input.rightKeyDown = false;
			input.sneak = false;
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void checkLock(MouseEvent event) {
		if(ClientWorking.isLocked)
			event.setCanceled(true);
	}
	
	private static final List<ClientChatReceivedEvent> tempTexts = Collections.synchronizedList(Lists.newLinkedList());
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onChatReceived(ClientChatReceivedEvent event) {
		if(ClientWorking.isLocked&&DialogTasksConfig.isCacheInDialogLock) {
			if(event.getType().getId()!=-1)
				tempTexts.add(event);
			event.setCanceled(true);
		}
		if(event.getType().getId()==-1)
			Minecraft.getMinecraft().player.sendMessage(event.getMessage());
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onPlayerTick(PlayerTickEvent event) {
		if(event.side==Side.CLIENT&&event.player.world.isRemote&&event.phase==TickEvent.Phase.END) {
			if(!ClientWorking.isLocked&&!tempTexts.isEmpty()) {
				for(ClientChatReceivedEvent text:tempTexts)
					Minecraft.getMinecraft().ingameGUI.addChatMessage(text.getType(), text.getMessage());
				tempTexts.clear();
			}else if(ClientWorking.isLocked) {
				KeyBinding.unPressAllKeys();
				Minecraft.getMinecraft().player.resetActiveHand();
				if(Minecraft.getMinecraft().player.isHandActive()) {
					Minecraft.getMinecraft().playerController.onStoppedUsingItem(Minecraft.getMinecraft().player);
				}
			}
		}
	}
	
}
