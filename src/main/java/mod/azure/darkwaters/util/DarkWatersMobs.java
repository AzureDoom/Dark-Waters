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

	public static void init() {
		ABERRATION = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("aberration"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, AberrationEntity::new).dimensions(EntityDimensions.fixed(0.9f, 2.05F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		MANARAW = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("manaraw"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, ManarawEntity::new).dimensions(EntityDimensions.scalable(3.6f, 3.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		MOHAST = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("mohast"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, MohastEntity::new).dimensions(EntityDimensions.scalable(2.2f, 0.65F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		SIGHT_HUNTER = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("sight_hunter"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, SightHunterEntity::new).dimensions(EntityDimensions.scalable(4.6f, 1.45F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		CRAEKEN = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("craeken"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, CraekenEntity::new).dimensions(EntityDimensions.scalable(4.6f, 1.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		MIRAID = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("miraid"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, MiraidEntity::new).dimensions(EntityDimensions.fixed(4.6f, 2.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		MIRAID_HALLUCINATION = Registry.register(Registry.ENTITY_TYPE, DarkWatersMod.modResource("miraid_hallucination"), FabricEntityTypeBuilder.create(MobCategory.MONSTER, MiraidHallucinationEntity::new).dimensions(EntityDimensions.fixed(1.6f, 3.45F)).trackRangeBlocks(90).trackedUpdateRate(1).build());
		FabricDefaultAttributeRegistry.register(ABERRATION, AberrationEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MANARAW, ManarawEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MOHAST, MohastEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(SIGHT_HUNTER, SightHunterEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(CRAEKEN, CraekenEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID, MiraidEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID_HALLUCINATION, MiraidHallucinationEntity.createMobAttributes());
	}
}