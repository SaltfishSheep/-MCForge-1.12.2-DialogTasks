package saltsheep.dialog.file.word.require;

import java.util.Map;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.DataManager;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.word.AFileWordRequireHandler;
import saltsheep.lib.nbt.SheepNBT;

public class WordVarInRange extends AFileWordRequireHandler {

	@Override
	public String methodWord() {
		return "varInRange";
	}

	@Override
	public String[] paramWords() {
		return new String[] {"var","max","min","isGlobal"};
	}

	@Override
	public Requirement getRequire(IRequireAddable addtor, Map<String, String> params) {
		double max = params.containsKey("max")? Integer.valueOf(params.get("max")):Integer.MAX_VALUE;
		double min = params.containsKey("min")? Integer.valueOf(params.get("min")):Integer.MIN_VALUE;
		String var = params.get("var");
		boolean isGlobal = false;
		if(params.containsKey("isGloabl")&&params.get("isGlobal").equalsIgnoreCase("true"))
			isGlobal=true;
		return new VarInRange(var,max,min,isGlobal);
	}
	
	public static class VarInRange implements Requirement{
		
		private final String var;
		private final double max,min;
		private final boolean isGlobal;
		public VarInRange(String var,double max,double min,boolean isGlobal) {
			this.var = var;
			this.max = max;
			this.min = min;
			this.isGlobal=isGlobal;
		}
		@Override
		public boolean apply(EntityPlayerMP player, EntityLivingBase hostingEntity) {
			SheepNBT nbt = DataManager.getData(player, isGlobal).getOrCreateCompound("vars");
			double value = nbt.getDouble(var);
			return value>=min&&value<=max;
		}
	}

}
