package saltsheep.dialog.file.word.run.var;

import java.util.List;
import java.util.regex.Pattern;

import com.google.common.collect.Lists;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.DialogTasks;

public class PlaceholderHandler {
	
	private static List<Placeholder> phs = Lists.newArrayList();
	
	public static void register(Placeholder ph) {
		if(phs.contains(ph))
			return;
		else {
			phs.add(ph);
			DialogTasks.info("Register placeholder successful,regex:"+ph.nameRegex());
		}
	}
	
	public static String handler(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp) {
		for(Placeholder ph:phs) {
			if(Pattern.compile(ph.nameRegex()).matcher(exp).find()) {
				exp = ph.replace(player, hostingEntity, exp);
			}
		}
		//SheepNBT nbt = PlayerHelper.getSheepData(player).getOrCreateCompound("vars");
		//for(String var:varNames) {
		//	exp = exp.replaceAll(new StringBuilder("%").append(var).append("%").toString(), String.valueOf(nbt.getDouble(var)));
		//}
		return exp;
	}
	
	public static interface Placeholder{
		
		String nameRegex();

		String replace(EntityPlayerMP player, EntityLivingBase hostingEntity, String exp);
	
		public static String[] getUseful(String exp, String regex) {
			char[] cs = exp.toCharArray();
			List<String> temp = Lists.newLinkedList();
			int left = 0;
			int has = 0;
			for(int i=0;i<cs.length;i++) {
				if(cs[i]=='%')
					if(has==0) {
						has++;
						left = i;
					}else {
						has--;
						temp.add(exp.substring(left+1, i));
					}
			}
			for(int i=temp.size()-1;i>=0;i--)
				if(!Pattern.compile(regex).matcher(temp.get(i)).find())
					temp.remove(i);
			return temp.toArray(new String[temp.size()]);
		}
	
	}
	
}
