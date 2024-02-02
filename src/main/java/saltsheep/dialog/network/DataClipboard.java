package saltsheep.dialog.network;

import io.netty.buffer.Unpooled;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLEventChannel;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.internal.FMLProxyPacket;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import saltsheep.lib.common.ClipboardHelper;
import saltsheep.lib.network.NetworkHelper;

public class DataClipboard{
	
	private static final String NAME = "CLIPBOARDDATA";
	static final FMLEventChannel CHANNEL = NetworkRegistry.INSTANCE.newEventDrivenChannel(NAME);
	
	public static void sendByServer(EntityPlayerMP player, String str) {
		PacketBuffer buf = new PacketBuffer(Unpooled.buffer());
		NetworkHelper.writeString(buf, str);
		CHANNEL.sendTo(new FMLProxyPacket(buf,NAME), player);
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public static void copyByClient(FMLNetworkEvent.ClientCustomPacketEvent event) {
		if(event.getPacket().channel().equals(NAME))
			ClipboardHelper.writeClip(NetworkHelper.readString(event.getPacket().payload()));
	}
	
}