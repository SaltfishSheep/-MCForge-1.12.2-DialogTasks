package saltsheep.dialog.network;

public class NetworkRegistryHandler {

	public static void register() {
		DataClipboard.CHANNEL.register(DataClipboard.class);
		DataPlayerLock.CHANNEL.register(DataPlayerLock.class);
		DataDialogReply.CHANNEL.register(DataDialogReply.class);
	}
	
}
