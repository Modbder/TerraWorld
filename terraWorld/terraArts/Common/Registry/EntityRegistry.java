package terraWorld.terraArts.Common.Registry;

import terraWorld.terraArts.Common.Entity.EntityFallingStar;
import terraWorld.terraArts.Mod.TerraArts;

public class EntityRegistry {
	public static void register()
	{
		cpw.mods.fml.common.registry.EntityRegistry.registerModEntity(EntityFallingStar.class, "fallingstar", TerraArts.cfg.mobID[0], TerraArts.instance, 32, 1, true);
	}
}
