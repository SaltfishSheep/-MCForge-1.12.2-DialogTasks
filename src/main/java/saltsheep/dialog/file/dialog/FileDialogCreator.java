package saltsheep.dialog.file.dialog;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRunAddable;
import saltsheep.dialog.file.word.run.var.PlaceholderHandler;

public class FileDialogCreator implements saltsheep.dialog.base.IDialogCreator,IRequireAddable,IRunAddable {

	public String dialogType;
	public EntityLivingBase hostingEntity;
	public String dialogID;
	public String[] dialogTexts;
	public String[] dialogTimesRaw;
	public List<FileDialogInterface> nextChains = Lists.newLinkedList();
	public Map<String, List<Requirement>> requires = Maps.newHashMap();
	public List<ExtraRun>[] runs;
	public boolean isMain = false;
	
	@Override
	public ADialogChainBase create(EntityPlayerMP player) {
		long[] dialogTimes = new long[dialogTimesRaw.length];
		for(int i=0;i<dialogTimesRaw.length;i++) {
			String exp = PlaceholderHandler.handler(player,hostingEntity,dialogTimesRaw[i]);
			double v = saltsheep.lib.math.MathExpression.get(exp);
			dialogTimes[i] = (long) v;
		}
		if(dialogType.equalsIgnoreCase("JS"))
			return new FileDialogChainJS(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		else if(dialogType.equalsIgnoreCase("MO"))
			return new FileDialogChainMO(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		else if(dialogType.equalsIgnoreCase("NO"))
			return new FileDialogChainNO(player, hostingEntity, dialogID, dialogTexts, dialogTimes, requires, runs);
		else if(dialogType.equalsIgnoreCase("NONL"))
			return new FileDialogChainNONL(player, hostingEntity, dialogID, dialogTexts, dialogTimes, requires, runs);
		else if(dialogType.equalsIgnoreCase("YON"))
			return new FileDialogChainYON(player, hostingEntity, dialogID, dialogTexts, dialogTimes, nextChains, requires, runs);
		return null;
	}
	
	public ADialogChainBase create(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity) {
		this.hostingEntity = hostingEntity;
		return this.create(player);
	}

	@Override
	public void addRun(ExtraRun add,Object...extraParams) {
		if(extraParams.length<1||!(extraParams[0] instanceof Integer))
			return;
		int line = (Integer)extraParams[0];
		int runIndex = line-1;
		if(this.runs[runIndex]==null)
			this.runs[runIndex] = Lists.newLinkedList();
		this.runs[runIndex].add(add);
	}

	@Override
	public void addRequire(Requirement add, Object... extraParams) {
		String group = (String) extraParams[0];
		if(!this.requires.containsKey(group))
			this.requires.put(group, Lists.newLinkedList());
		this.requires.get(group).add(add);
	}
	
	public boolean isEmpty() {
		return this.dialogType==null||this.dialogID==null||this.dialogTexts==null;
	}

}
