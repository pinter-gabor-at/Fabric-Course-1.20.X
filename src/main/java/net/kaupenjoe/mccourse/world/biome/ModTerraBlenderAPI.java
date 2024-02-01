package net.kaupenjoe.mccourse.world.biome;

import net.kaupenjoe.mccourse.MCCourseMod;
import net.kaupenjoe.mccourse.util.ModIdentifier;
import net.kaupenjoe.mccourse.world.biome.surface.ModMaterialRules;
import terrablender.api.Regions;
import terrablender.api.SurfaceRuleManager;
import terrablender.api.TerraBlenderApi;

public class ModTerraBlenderAPI implements TerraBlenderApi {
	@Override
	public void onTerraBlenderInitialized() {
		Regions.register(new ModOverworldRegion(new ModIdentifier("overworld"), 4));

		SurfaceRuleManager.addSurfaceRules(
			SurfaceRuleManager.RuleCategory.OVERWORLD,
			MCCourseMod.MOD_ID,
			ModMaterialRules.makeRules());
	}
}
