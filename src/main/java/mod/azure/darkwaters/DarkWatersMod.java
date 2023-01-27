package mod.azure.darkwaters;

import java.awt.Color;

import eu.midnightdust.lib.config.MidnightConfig;
import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.effect.StormDarknessEffect;
import mod.azure.darkwaters.items.DarkSpawnEgg;
import mod.azure.darkwaters.util.DarkWatersMobs;
import mod.azure.darkwaters.util.DarkWatersSounds;
import mod.azure.darkwaters.util.DarkWatersSpawning;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class DarkWatersMod implements ModInitializer {

	public static final String MODID = "darkwaters";
	public static DarkWatersMobs MOBS;
	public static DarkWatersSounds SOUNDS;
	public static final MobEffect STORMDARKNESS = new StormDarknessEffect(MobEffectCategory.BENEFICIAL,
			new Color(0, 0, 0).getRGB());
	public static final CreativeModeTab GENERAL = FabricItemGroup
			.builder(new ResourceLocation(DarkWatersMod.MODID, "itemgroup"))
			.icon(() -> new ItemStack(DarkWatersMod.ABERRATION_SPAWN_EGG))
			.displayItems((enabledFeatures, entries, operatorEnabled) -> {
				entries.accept(DarkWatersMod.ABERRATION_SPAWN_EGG);
				entries.accept(DarkWatersMod.MANARAW_SPAWN_EGG);
				entries.accept(DarkWatersMod.MOHAST_SPAWN_EGG);
				entries.accept(DarkWatersMod.SIGHT_HUNTER_SPAWN_EGG);
				entries.accept(DarkWatersMod.CRAEKEN_SPAWN_EGG);
				entries.accept(DarkWatersMod.MIRAID_SPAWN_EGG);
			}).build();
	public static DarkSpawnEgg ABERRATION_SPAWN_EGG;
	public static DarkSpawnEgg MANARAW_SPAWN_EGG;
	public static DarkSpawnEgg MOHAST_SPAWN_EGG;
	public static DarkSpawnEgg SIGHT_HUNTER_SPAWN_EGG;
	public static DarkSpawnEgg CRAEKEN_SPAWN_EGG;
	public static DarkSpawnEgg MIRAID_SPAWN_EGG;

	@Override
	public void onInitialize() {
		MidnightConfig.init(MODID, DarkWatersConfig.class);
		MOBS = new DarkWatersMobs();
		SOUNDS = new DarkWatersSounds();
		DarkWatersSpawning.addSpawnEntries();
		ABERRATION_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM,
				new ResourceLocation(MODID, "aberration_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.ABERRATION, 0x150056, 0x826ccc));
		MANARAW_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "manaraw_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MANARAW, 0x181c59, 0x636b6d));
		MOHAST_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "mohast_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MOHAST, 0x477385, 0xacb7b7));
		SIGHT_HUNTER_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM,
				new ResourceLocation(MODID, "sighthunter_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.SIGHT_HUNTER, 0x01293a, 0x808f95));
		CRAEKEN_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "craeken_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.CRAEKEN, 0xada7a2, 0xcee3e3));
		MIRAID_SPAWN_EGG = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MODID, "miraid_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MIRAID, 0x5d5d6e, 0xd6d6d6));
		Registry.register(BuiltInRegistries.MOB_EFFECT, new ResourceLocation(MODID, "storm_darkness"), STORMDARKNESS);
		DarkWatersMobs.init();
	}
}
