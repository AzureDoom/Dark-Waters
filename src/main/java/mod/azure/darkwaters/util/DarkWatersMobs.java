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
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class DarkWatersMobs {

	public static final EntityType<AberrationEntity> ABERRATION = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "aberration"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, AberrationEntity::new)
					.dimensions(EntityDimensions.fixed(0.9f, 2.05F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<ManarawEntity> MANARAW = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "manaraw"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, ManarawEntity::new)
					.dimensions(EntityDimensions.scalable(3.6f, 3.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<MohastEntity> MOHAST = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "mohast"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, MohastEntity::new)
					.dimensions(EntityDimensions.scalable(2.2f, 0.65F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<SightHunterEntity> SIGHT_HUNTER = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "sight_hunter"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, SightHunterEntity::new)
					.dimensions(EntityDimensions.scalable(4.6f, 1.45F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<CraekenEntity> CRAEKEN = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "craeken"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, CraekenEntity::new)
					.dimensions(EntityDimensions.scalable(4.6f, 1.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<MiraidEntity> MIRAID = Registry.register(BuiltInRegistries.ENTITY_TYPE,
			new ResourceLocation(DarkWatersMod.MODID, "miraid"),
			FabricEntityTypeBuilder.create(MobCategory.MONSTER, MiraidEntity::new)
					.dimensions(EntityDimensions.fixed(4.6f, 2.95F)).trackRangeBlocks(90).trackedUpdateRate(1).build());

	public static final EntityType<MiraidHallucinationEntity> MIRAID_HALLUCINATION = Registry
			.register(BuiltInRegistries.ENTITY_TYPE, new ResourceLocation(DarkWatersMod.MODID, "miraid_hallucination"),
					FabricEntityTypeBuilder.create(MobCategory.MONSTER, MiraidHallucinationEntity::new)
							.dimensions(EntityDimensions.fixed(1.6f, 3.45F)).trackRangeBlocks(90).trackedUpdateRate(1)
							.build());

	public static void init() {
		FabricDefaultAttributeRegistry.register(ABERRATION, AberrationEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MANARAW, ManarawEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MOHAST, MohastEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(SIGHT_HUNTER, SightHunterEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(CRAEKEN, CraekenEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID, MiraidEntity.createMobAttributes());
		FabricDefaultAttributeRegistry.register(MIRAID_HALLUCINATION, MiraidHallucinationEntity.createMobAttributes());
	}
}