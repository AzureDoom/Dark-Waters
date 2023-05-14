package mod.azure.darkwaters;

import java.awt.Color;

import mod.azure.azurelib.AzureLibMod;
import mod.azure.azurelib.config.format.ConfigFormats;
import mod.azure.azurelib.items.AzureSpawnEgg;
import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.effect.StormDarknessEffect;
import mod.azure.darkwaters.entity.helper.AttackType;
import mod.azure.darkwaters.util.DarkWatersMobs;
import mod.azure.darkwaters.util.DarkWatersSounds;
import mod.azure.darkwaters.util.DarkWatersSpawning;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
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
	public static DarkWatersConfig config;
	public static DarkWatersMobs MOBS;
	public static DarkWatersSounds SOUNDS;
	public static final MobEffect STORMDARKNESS = new StormDarknessEffect(MobEffectCategory.BENEFICIAL, new Color(0, 0, 0).getRGB());
	public static final CreativeModeTab GENERAL = FabricItemGroup.builder(modResource("itemgroup")).icon(() -> new ItemStack(DarkWatersMod.ABERRATION_SPAWN_EGG)).displayItems((context, entries) -> {
		entries.accept(DarkWatersMod.ABERRATION_SPAWN_EGG);
		entries.accept(DarkWatersMod.MANARAW_SPAWN_EGG);
		entries.accept(DarkWatersMod.MOHAST_SPAWN_EGG);
		entries.accept(DarkWatersMod.SIGHT_HUNTER_SPAWN_EGG);
		entries.accept(DarkWatersMod.CRAEKEN_SPAWN_EGG);
		entries.accept(DarkWatersMod.MIRAID_SPAWN_EGG);
	}).build();
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
	public static AzureSpawnEgg ABERRATION_SPAWN_EGG;
	public static AzureSpawnEgg MANARAW_SPAWN_EGG;
	public static AzureSpawnEgg MOHAST_SPAWN_EGG;
	public static AzureSpawnEgg SIGHT_HUNTER_SPAWN_EGG;
	public static AzureSpawnEgg CRAEKEN_SPAWN_EGG;
	public static AzureSpawnEgg MIRAID_SPAWN_EGG;

	public static final ResourceLocation modResource(String name) {
		return new ResourceLocation(MODID, name);
	}

	@Override
	public void onInitialize() {
		config = AzureLibMod.registerConfig(DarkWatersConfig.class, ConfigFormats.json()).getConfigInstance();
		DarkWatersMobs.init();
		SOUNDS = new DarkWatersSounds();
		DarkWatersSpawning.addSpawnEntries();
		EntityDataSerializers.registerSerializer(ATTACK_TYPE);
		ABERRATION_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("aberration_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.ABERRATION, 0x150056, 0x826ccc));
		MANARAW_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("manaraw_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.MANARAW, 0x181c59, 0x636b6d));
		MOHAST_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("mohast_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.MOHAST, 0x477385, 0xacb7b7));
		SIGHT_HUNTER_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("sighthunter_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.SIGHT_HUNTER, 0x01293a, 0x808f95));
		CRAEKEN_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("craeken_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.CRAEKEN, 0xada7a2, 0xcee3e3));
		MIRAID_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, modResource("miraid_spawn_egg"), new AzureSpawnEgg(DarkWatersMobs.MIRAID, 0x5d5d6e, 0xd6d6d6));
		Registry.register(BuiltInRegistries.MOB_EFFECT, modResource("storm_darkness"), STORMDARKNESS);
	}
}
