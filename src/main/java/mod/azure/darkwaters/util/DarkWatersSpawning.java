package mod.azure.darkwaters.util;

import java.util.Arrays;
import java.util.List;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;

@SuppressWarnings("deprecation")
public class DarkWatersSpawning {

	public static List<String> biomes = Arrays.asList("#ocean");

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(biomes, context)),
				SpawnGroup.MONSTER, DarkWatersMobs.ABERRATION, 10, 1, 1);
	}

	private static boolean parseBiomes(List<String> biomes, BiomeSelectionContext biomeContext) {
		return biomes.contains(biomeContext.getBiomeKey().getValue().toString())
				|| biomes.contains("#" + biomeContext.getBiome().getCategory().asString());
	}
}