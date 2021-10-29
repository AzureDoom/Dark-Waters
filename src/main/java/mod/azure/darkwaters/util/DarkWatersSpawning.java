package mod.azure.darkwaters.util;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

@SuppressWarnings("deprecation")
public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.ABERRATION, 10, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MOHAST, 10, 3, 10);
	}
}