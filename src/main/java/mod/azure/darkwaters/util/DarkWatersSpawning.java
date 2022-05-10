package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.AberrationEntity;
import mod.azure.darkwaters.entity.CraekenEntity;
import mod.azure.darkwaters.entity.ManarawEntity;
import mod.azure.darkwaters.entity.MiraidEntity;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import mod.azure.darkwaters.entity.MohastEntity;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.mixin.object.builder.SpawnRestrictionAccessor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.world.Heightmap;

public class DarkWatersSpawning {

	public static void addSpawnEntries() {
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.ABERRATION, DarkWatersMod.config.spawning.aberration_spawnweight, 1, 1);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MOHAST, DarkWatersMod.config.spawning.mohast_spawnweight, 1, 4);
		BiomeModifications.addSpawn(BiomeSelectors.spawnsOneOf(EntityType.DOLPHIN), SpawnGroup.WATER_CREATURE,
				DarkWatersMobs.MIRAID, DarkWatersMod.config.spawning.miraid_spawnweight, 1, 1);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.ABERRATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, AberrationEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.CRAEKEN, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, CraekenEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.MANARAW, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, ManarawEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.MIRAID, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, MiraidEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.MIRAID_HALLUCINATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, MiraidHallucinationEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.MOHAST, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, MohastEntity::canSpawnInDarkWater);
		SpawnRestrictionAccessor.callRegister(DarkWatersMobs.SIGHT_HUNTER, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, SightHunterEntity::canSpawnInDarkWater);
	}
}