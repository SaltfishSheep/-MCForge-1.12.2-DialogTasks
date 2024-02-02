package saltsheep.dialog.file;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public interface IRunAddable {
	
	public void addRun(ExtraRun add,Object... extraParams);

	public static interface ExtraRun{
		public boolean run(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity);
	}

}
