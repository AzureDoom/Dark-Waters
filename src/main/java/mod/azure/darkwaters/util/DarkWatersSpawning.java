package mod.azure.darkwaters.util;

import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;

public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.ABERRATION, DarkWatersConfig.aberration_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.CRAEKEN, DarkWatersConfig.craeken_spawnweight, 1, 1);
//		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.MANARAW, DarkWatersConfig.manaraw_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.MIRAID, DarkWatersConfig.miraid_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.MIRAID_HALLUCINATION, DarkWatersConfig.miraid_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.MOHAST, DarkWatersConfig.mohast_spawnweight, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), MobCategory.MONSTER, DarkWatersMobs.SIGHT_HUNTER, DarkWatersConfig.sighthunter_spawnweight, 1, 1);
		SpawnPlacements.register(DarkWatersMobs.ABERRATION, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.CRAEKEN, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MANARAW, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MIRAID, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MIRAID_HALLUCINATION, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MOHAST, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.SIGHT_HUNTER, SpawnPlacements.Type.IN_WATER, Heightmap.Types.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
	}
}