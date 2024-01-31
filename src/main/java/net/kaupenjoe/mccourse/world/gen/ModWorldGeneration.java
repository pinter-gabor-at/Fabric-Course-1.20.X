package net.kaupenjoe.mccourse.world.gen;

public class ModWorldGeneration {
	public static void generateModWorldGeneration() {
		ModTreeGeneration.generateTrees();
		ModOreGeneration.generateOres();
		ModFlowerGeneration.generateFlowers();
		ModEntitySpawns.addSpawns();
	}
}
