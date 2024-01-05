package net.kaupenjoe.mccourse.util;

import net.kaupenjoe.mccourse.MCCourseMod;

import net.minecraft.util.Identifier;

public class ModIdentifier extends Identifier {
	/**
	 * Create a mod specific identifier
	 * @param path Name, as in lang/*.json files without "*.modid." prefix
	 */
	public ModIdentifier(String path) {
		super(MCCourseMod.MOD_ID, path);
	}
}
