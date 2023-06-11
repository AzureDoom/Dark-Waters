package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.BaseWaterEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;

public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.ABERRATION, DarkWatersMod.config.aberration_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.CRAEKEN, DarkWatersMod.config.craeken_spawnweight, 1, 1);
//		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.MANARAW, DarkWatersMod.config.manaraw_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.MIRAID, DarkWatersMod.config.miraid_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.MIRAID_HALLUCINATION, DarkWatersMod.config.miraid_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.MOHAST, DarkWatersMod.config.mohast_spawnweight, 1, 3);
		BiomeModifications.addSpawn(BiomeSelectors.all().and(context -> parseBiomes(DarkWatersMod.DARKWATER_BIOMES, context)), MobCategory.AMBIENT, DarkWatersMobs.SIGHT_HUNTER, DarkWatersMod.config.sighthunter_spawnweight, 1, 1);
		SpawnPlacements.register(DarkWatersMobs.ABERRATION, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.CRAEKEN, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MANARAW, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MIRAID, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MIRAID_HALLUCINATION, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.MOHAST, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
		SpawnPlacements.register(DarkWatersMobs.SIGHT_HUNTER, SpawnPlacements.Type.IN_WATER, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, BaseWaterEntity::canSpawnInDarkWater);
	}

	private static boolean parseBiomes(TagKey<Biome> biomes, BiomeSelectionContext biomeContext) {
		return biomeContext.hasTag(biomes);
	}
}