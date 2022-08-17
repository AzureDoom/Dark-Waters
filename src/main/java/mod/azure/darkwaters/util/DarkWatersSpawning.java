package mod.azure.darkwaters.util;

import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.entity.AberrationEntity;
import mod.azure.darkwaters.entity.CraekenEntity;
import mod.azure.darkwaters.entity.ManarawEntity;
import mod.azure.darkwaters.entity.MiraidEntity;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import mod.azure.darkwaters.entity.MohastEntity;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.ABERRATION, DarkWatersConfig.aberration_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MOHAST, DarkWatersConfig.mohast_spawnweight, 1, 4);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MIRAID, DarkWatersConfig.miraid_spawnweight, 1, 1);
		SpawnRestriction.register(DarkWatersMobs.ABERRATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, AberrationEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.CRAEKEN, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, CraekenEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.MANARAW, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, ManarawEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.MIRAID, SpawnRestriction.Location.IN_WATER, Heightmap.Type.OCEAN_FLOOR,
				MiraidEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.MIRAID_HALLUCINATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, MiraidHallucinationEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.MOHAST, SpawnRestriction.Location.IN_WATER, Heightmap.Type.OCEAN_FLOOR,
				MohastEntity::canSpawnInDarkWater);
		SpawnRestriction.register(DarkWatersMobs.SIGHT_HUNTER, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, SightHunterEntity::canSpawnInDarkWater);
	}
}