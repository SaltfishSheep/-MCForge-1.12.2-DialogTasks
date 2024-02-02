package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import saltsheep.dialog.DialogTasks;

//*No Options
public abstract class ADialogChainNO extends ADialogChainBase {

	public ADialogChainNO(EntityPlayerMP player) {
		super(player);
	}

	@Override
	public void runChain() {
		this.lockPlayer();
		String[] texts = this.getDialogTextsPlayer();
		long[] times = this.getDialogTimes();
		new Thread(()->{
			for(int each = 0;each<texts.length&&each<times.length;each++) {
				final int tempEach = each;
				MinecraftServer MCServer = FMLCommonHandler.instance().getMinecraftServerInstance();
				MCServer.addScheduledTask(()->
					this.player.connection.sendPacket(new SPacketChat(new TextComponentString(texts[tempEach]),Initer.DIALOG))
				);
				try {
					if(times!=null&&times[each]>0)
						Thread.sleep(times[each]);
				} catch (Throwable error) {
					DialogTasks.printError(error);
				}
				MCServer.addScheduledTask(()->this.runExtra(tempEach));
			}
			this.countRunTimes();
			this.unLockPlayer();
		}).start();
	}

}
