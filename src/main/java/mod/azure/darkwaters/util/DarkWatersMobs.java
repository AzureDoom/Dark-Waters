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
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DarkWatersMobs {

	public static final EntityType<AberrationEntity> ABERRATION = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "aberration"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, AberrationEntity::new)
					.dimensions(EntityDimensions.fixed(0.9f, 2.05F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<ManarawEntity> MANARAW = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "manaraw"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, ManarawEntity::new)
					.dimensions(EntityDimensions.changing(15.6f, 3.95F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<MohastEntity> MOHAST = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "mohast"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MohastEntity::new)
					.dimensions(EntityDimensions.changing(2.2f, 0.65F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<SightHunterEntity> SIGHT_HUNTER = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "sight_hunter"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, SightHunterEntity::new)
					.dimensions(EntityDimensions.changing(4.6f, 1.45F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<CraekenEntity> CRAEKEN = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "craeken"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CraekenEntity::new)
					.dimensions(EntityDimensions.changing(12.6f, 2.95F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<MiraidEntity> MIRAID = Registry.register(Registry.ENTITY_TYPE,
			new Identifier(DarkWatersMod.MODID, "miraid"),
			FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MiraidEntity::new)
					.dimensions(EntityDimensions.fixed(4.6f, 2.95F)).trackRangeBlocks(90).trackedUpdateRate(4).build());

	public static final EntityType<MiraidHallucinationEntity> MIRAID_HALLUCINATION = Registry
			.register(Registry.ENTITY_TYPE, new Identifier(DarkWatersMod.MODID, "miraid_hallucination"),
					FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, MiraidHallucinationEntity::new)
							.dimensions(EntityDimensions.fixed(1.6f, 3.45F)).trackRangeBlocks(90).trackedUpdateRate(4)
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