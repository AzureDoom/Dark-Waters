package mod.azure.darkwaters;

import java.awt.Color;

import eu.midnightdust.lib.config.MidnightConfig;
import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.effect.StormDarknessEffect;
import mod.azure.darkwaters.entity.helper.AttackType;
import mod.azure.darkwaters.util.DarkWatersMobs;
import mod.azure.darkwaters.util.DarkWatersSounds;
import mod.azure.darkwaters.util.DarkWatersSpawning;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.syncher.EntityDataSerializer;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DarkWatersMod implements ModInitializer {

	public static final String MODID = "darkwaters";
	public static DarkWatersMobs MOBS;
	public static DarkWatersSounds SOUNDS;
	public static final MobEffect STORMDARKNESS = new StormDarknessEffect(MobEffectCategory.BENEFICIAL, new Color(0, 0, 0).getRGB());
	public static final CreativeModeTab GENERAL = FabricItemGroupBuilder.build(modResource("itemgroup"), () -> new ItemStack(DarkWatersMod.ABERRATION_SPAWN_EGG));
	public static final EntityDataSerializer<AttackType> ATTACK_TYPE = new EntityDataSerializer<>() {
		@Override
		public void write(FriendlyByteBuf packetByteBuf, AttackType alienAttackType) {
			packetByteBuf.writeEnum(alienAttackType);
		}

		@Override
		public AttackType read(FriendlyByteBuf packetByteBuf) {
			return packetByteBuf.readEnum(AttackType.class);
		}

		@Override
		public AttackType copy(AttackType alienAttackType) {
			return alienAttackType;
		}
	};
	public static DarkWaterSpawnEgg ABERRATION_SPAWN_EGG;
	public static DarkWaterSpawnEgg MANARAW_SPAWN_EGG;
	public static DarkWaterSpawnEgg MOHAST_SPAWN_EGG;
	public static DarkWaterSpawnEgg SIGHT_HUNTER_SPAWN_EGG;
	public static DarkWaterSpawnEgg CRAEKEN_SPAWN_EGG;
	public static DarkWaterSpawnEgg MIRAID_SPAWN_EGG;

	public static final ResourceLocation modResource(String name) {
		return new ResourceLocation(MODID, name);
	}

	@Override
	public void onInitialize() {
		MidnightConfig.init(MODID, DarkWatersConfig.class);
		DarkWatersMobs.init();
		SOUNDS = new DarkWatersSounds();
		DarkWatersSpawning.addSpawnEntries();
		EntityDataSerializers.registerSerializer(ATTACK_TYPE);
		ABERRATION_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("aberration_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.ABERRATION, 0x150056, 0x826ccc));
		MANARAW_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("manaraw_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.MANARAW, 0x181c59, 0x636b6d));
		MOHAST_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("mohast_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.MOHAST, 0x477385, 0xacb7b7));
		SIGHT_HUNTER_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("sighthunter_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.SIGHT_HUNTER, 0x01293a, 0x808f95));
		CRAEKEN_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("craeken_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.CRAEKEN, 0xada7a2, 0xcee3e3));
		MIRAID_SPAWN_EGG = Registry.register(Registry.ITEM, modResource("miraid_spawn_egg"), new DarkWaterSpawnEgg(DarkWatersMobs.MIRAID, 0x5d5d6e, 0xd6d6d6));
		Registry.register(Registry.MOB_EFFECT, modResource("storm_darkness"), STORMDARKNESS);
	}
}
