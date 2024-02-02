package saltsheep.dialog.file.word.run.var;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.DataManager;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.IRunAddable.ExtraRun;
import saltsheep.dialog.file.word.AFileWordRunHandler;
import saltsheep.lib.nbt.SheepNBT;

public class WordSetVar extends AFileWordRunHandler {

	@Override
	public String methodWord() {
		return "setVar";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"var","value","isGlobal"};
	}

	@Override
	public ExtraRun getRun(IRunAddable creator, Map<String, String> params) {
		String var = params.get("var");
		double value = Double.valueOf(params.get("value"));
		boolean isGlobal = false;
		if(params.containsKey("isGloabl")&&params.get("isGlobal").equalsIgnoreCase("true"))
			isGlobal=true;
		return new SetVar(var,value,isGlobal);
	}
	
	public static class SetVar implements ExtraRun{
		private final String var;
		private final double value;
		private final boolean isGlobal;
		public SetVar(String var,double value,boolean isGlobal) {this.var = var;this.value = value;this.isGlobal=isGlobal;}
		@Override
		public boolean run(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			SheepNBT nbt = DataManager.getData(player, isGlobal).getOrCreateCompound("vars");
			nbt.setTag(var, (double)value);
			return true;
		}
	}

}
