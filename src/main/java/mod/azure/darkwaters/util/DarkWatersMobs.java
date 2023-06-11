package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.entity.AberrationEntity;
import mod.azure.darkwaters.entity.CraekenEntity;
import mod.azure.darkwaters.entity.ManarawEntity;
import mod.azure.darkwaters.entity.MiraidEntity;
import mod.azure.darkwaters.entity.MiraidHallucinationEntity;
import mod.azure.darkwaters.entity.MohastEntity;
import mod.azure.darkwaters.entity.SightHunterEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class DarkWatersMobs {

	public static EntityType<AberrationEntity> ABERRATION;
	public static EntityType<ManarawEntity> MANARAW;
	public static EntityType<MohastEntity> MOHAST;
	public static EntityType<SightHunterEntity> SIGHT_HUNTER;
	public static EntityType<CraekenEntity> CRAEKEN;
	public static EntityType<MiraidEntity> MIRAID;
	public static EntityType<MiraidHallucinationEntity> MIRAID_HALLUCINATION;

	private static <T extends Entity> EntityType<T> mob(String id, EntityType.EntityFactory<T> factory, float height, float width) {
		final var type = FabricEntityTypeBuilder.<T>create(MobCategory.AMBIENT, factory).dimensions(EntityDimensions.scalable(height, width)).fireImmune().trackedUpdateRate(1).trackRangeBlocks(90).build();
		Registry.register(BuiltInRegistries.ENTITY_TYPE, DarkWatersMod.modResource(id), type);

		return type;
	}

	public static void init() {
		ABERRATION = mob("aberration", AberrationEntity::new, 0.9f, 2.05F);
		MANARAW = mob("manaraw", ManarawEntity::new, 3.6f, 3.95F);
		MOHAST = mob("mohast", MohastEntity::new, 2.2f, 0.65F);
		SIGHT_HUNTER = mob("sight_hunter", SightHunterEntity::new, 4.6f, 1.45F);
		CRAEKEN = mob("craeken", CraekenEntity::new, 4.6f, 1.95F);
		MIRAID = mob("miraid", MiraidEntity::new, 4.6f, 2.95F);
		MIRAID_HALLUCINATION = mob("miraid_hallucination", MiraidHallucinationEntity::new, 1.6f, 3.45F);
		FabricDefaultAttributeRegistry.register(ABERRATION, AberrationEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MANARAW, ManarawEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MOHAST, MohastEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(SIGHT_HUNTER, SightHunterEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(CRAEKEN, CraekenEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID, MiraidEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID_HALLUCINATION, MiraidHallucinationEntity.createMobAttributes());
	}
}