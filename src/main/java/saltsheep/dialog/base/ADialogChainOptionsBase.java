package saltsheep.dialog.base;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.FMLCommonHandler;
import saltsheep.dialog.DialogTasks;

public abstract class ADialogChainOptionsBase extends ADialogChainBase {

	protected List<FDialogInterface> chainsInterfaceHook = Lists.newArrayList();
	//*It means get the object like a hook,and give it to reply.
	
	public ADialogChainOptionsBase(EntityPlayerMP player) {
		super(player);
	}
	
	public abstract void reply(int key);
	
	//*初始化chainsInterfaceHook,当chainsInterfaceHook为空，reply不会正常执行
	protected abstract void initChainsInterfaceHooks();

	protected abstract String[] getNextChainsTextsAndRemoveUnrunnableChains();
	
	@Override
	public void runChain() {
		this.lockPlayer();
		this.initChainsInterfaceHooks();
		String[] texts = this.getDialogTextsPlayer();
		long[] times = this.getDialogTimes();
		new Thread(()->{
			MinecraftServer MCServer = FMLCommonHandler.instance().getMinecraftServerInstance();
			for(int each = 0;each<texts.length&&each<times.length;each++) {
				final int tempEach = each;
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
			MCServer.addScheduledTask(()->{
				String[] nextTexts = this.getNextChainsTextsAndRemoveUnrunnableChains();
				if(nextTexts!=null&&nextTexts.length>0) {
					for(String each : nextTexts)
						this.player.connection.sendPacket(new SPacketChat(new TextComponentString(each),Initer.DIALOG));
				}else
					this.unLockPlayer();
				this.countRunTimes();
			});
		}).start();
	}
	
}
