package saltsheep.dialog.file;

import javax.annotation.Nullable;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayerMP;

public interface IRequireAddable {

	public void addRequire(Requirement add,Object... extraParams);
	
	public static interface Requirement{
		public boolean apply(EntityPlayerMP player,@Nullable EntityLivingBase hostingEntity);
	}
	
}
