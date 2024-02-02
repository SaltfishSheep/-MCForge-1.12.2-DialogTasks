package saltsheep.dialog.file.dialog;

import java.util.List;
import java.util.Map;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;
import saltsheep.dialog.base.ADialogChainBase;
import saltsheep.dialog.file.IRequireAddable;
import saltsheep.dialog.file.IRequireAddable.Requirement;
import saltsheep.dialog.file.IRunAddable;

public abstract class AFileDialogChainBase extends ADialogChainBase {

	protected EntityLivingBase hostingEntity;
	protected String dialogID;
	protected String[] dialogTexts;
	protected long[] dialogTimes;
	protected Map<String,List<IRequireAddable.Requirement>> requires;
	protected List<IRunAddable.ExtraRun>[] runs;
	
	public AFileDialogChainBase(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity,String dialogID,String[] dialogTexts,long[] dialogTimes,Map<String, List<Requirement>> requires,List<IRunAddable.ExtraRun>[] runs) {
		super(player);
		this.hostingEntity = hostingEntity;
		this.dialogID = dialogID;
		this.dialogTexts = dialogTexts;
		this.dialogTimes = dialogTimes;
		this.requires = requires;
		this.runs = runs;
	}

	@Override
	protected String getID() {
		return this.dialogID;
	}

	@Override
	protected String[] getDialogTexts() {
		return this.dialogTexts;
	}

	@Override
	protected long[] getDialogTimes() {
		return this.dialogTimes;
	}

	@Override
	protected void runExtra(int point) {
		List<IRunAddable.ExtraRun> willRun = this.runs[point];
		if(willRun!=null&&!willRun.isEmpty())
			for(IRunAddable.ExtraRun run:willRun)
				run.run(this.player, this.hostingEntity);
	}

	@Override
	public boolean canRun() {
		if(this.requires.isEmpty())
			return true;
		up:for(List<Requirement> requires:this.requires.values()) {
			for(Requirement require:requires)
				if(!require.apply(player, null))
					continue up;
			return super.canRun();
		}
		return false;
	}
	

}
