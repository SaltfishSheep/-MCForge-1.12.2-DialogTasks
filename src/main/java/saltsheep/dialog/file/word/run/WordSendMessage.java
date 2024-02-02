package saltsheep.dialog.file.word.run;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.play.server.SPacketChat;
import net.minecraft.util.text.TextComponentString;
import saltsheep.dialog.base.Initer;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler;

public class WordSendMessage extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "sendMessage";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"text"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String text = params.get("text");
		return new SendMessage(text);
	}
	
	public static class SendMessage implements ExtraRun{
		private final String text;
		public SendMessage(String text) {this.text = text;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			String send = PlaceholderHandler.handler(player, hostingEntity, (text.replaceAll("%PLAYER%", player.getName())));
			player.connection.sendPacket(new SPacketChat(new TextComponentString(send),Initer.DIALOG));
			return true;
		}
	}

}
