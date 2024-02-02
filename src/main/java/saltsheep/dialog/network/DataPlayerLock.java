package saltsheep.dialog.network;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import saltsheep.dialog.event.ClientWorking;

public class DataPlayerLock{
	
	private static final String NAME = "PLAYERLOCKDATA";
	static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
	
	public static void lockByServer(EntityPlayerMP player) {
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		buf.writeBoolean(true);
		CHANNEL.sendTo(new FMLProxyPacket(buf,NAME), player);
	}
	
	public static void unlockByServer(EntityPlayerMP player) {
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		buf.writeBoolean(false);
		CHANNEL.sendTo(new FMLProxyPacket(buf,NAME), player);
	}
	
	@SubscribeEvent
	public static void lockOrUnlockByClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
		if(event.getPacket().channel().equals(NAME))
			ClientWorking.isLocked = event.getPacket().payload().readBoolean();
	}
	
}