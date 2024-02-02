package saltsheep.dialog.base;

import net.minecraft.entity.player.EntityPlayerMP;

public class FDialogInterface {

	private final IDialogCreator value;
	
	private final String text;

	public FDialogInterface(String text,IDialogCreator value) {
		this.text = text;
		this.value = value;
	}
	
	public String getText(EntityPlayerMP player) {
		return text.replaceAll("%PLAYER%", player.getName());
	}

	public ADialogChainBase createChain(EntityPlayerMP player) {
		return this.value.create(player);
	}
	
}
