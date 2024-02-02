package saltsheep.dialog.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import saltsheep.dialog.base.ADialogChainOptionsBase;
import saltsheep.dialog.base.FDialogHandler;
import saltsheep.lib.network.NetworkHelper;

public class DataDialogReply{
	
	private static final String NAME = "DIALOGREPLYDATA";
	static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
	
	static final Object lock = new Object();
	
	@SubscribeEvent
	public static void receivePacketFromClient(FMLNetworkEvent.ServerCustomPacketEvent event) {
		if(event.getPacket().channel().equals(NAME)) {
			synchronized(lock){
				ByteBuf buf = event.getPacket().payload();
				String playerName = NetworkHelper.readString(buf);
				final int key = buf.readInt();
				if(FDialogHandler.playersRunningDialog.get(playerName) instanceof ADialogChainOptionsBase) {
					ADialogChainOptionsBase dialogOptions = ((ADialogChainOptionsBase)(FDialogHandler.playersRunningDialog.get(playerName)));
					dialogOptions.reply(key);
				}
			}
		}
	}
	
	public static void sendPacketToServer(EntityPlayer player, int key) {
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		NetworkHelper.writeString(buf, player.getName());
		//*写入玩家名称
		buf.writeInt(key);
		CHANNEL.sendToServer(new FMLProxyPacket(buf,NAME));
	}
	
}