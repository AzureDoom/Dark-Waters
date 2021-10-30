package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

@SuppressWarnings("deprecation")
public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.ABERRATION, DarkWatersMod.config.spawning.aberration_spawnweight,
				DarkWatersMod.config.spawning.aberration_spawnmin, DarkWatersMod.config.spawning.aberration_spawnmax);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MOHAST, DarkWatersMod.config.spawning.mohast_spawnweight,
				DarkWatersMod.config.spawning.mohast_spawnmin, DarkWatersMod.config.spawning.mohast_spawnmax);
	}
}