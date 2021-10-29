package mod.azure.darkwaters.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import mod.azure.darkwaters.entity.BaseWaterEntity;
import mod.azure.darkwaters.util.DarkWatersMobs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;

@Mixin(SpawnRestriction.class)
public class SpawnRestrictionMixin {

	@Shadow
	private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location,
			Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
	}

	static {
		register(DarkWatersMobs.ABERRATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.CRAEKEN, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.MANARAW, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.MIRAID, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.MIRAID_HALLUCINATION, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.MOHAST, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
		register(DarkWatersMobs.SIGHT_HUNTER, SpawnRestriction.Location.IN_WATER,
				Heightmap.Type.OCEAN_FLOOR, BaseWaterEntity::canSpawnInDarkWater);
	}
}