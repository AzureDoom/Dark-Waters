package mod.azure.darkwaters;

import mod.azure.darkwaters.items.DarkSpawnEgg;
import mod.azure.darkwaters.util.DarkWatersMobs;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DarkWatersMod implements ModInitializer {

	public static final String MODID = "darkwaters";
	public static DarkWatersMobs MOBS;
	public static DarkWatersSounds SOUNDS;

	@Override
	public void onInitialize() {
		MOBS = new DarkWatersMobs();
		SOUNDS = new DarkWatersSounds();
		Registry.register(Registry.ITEM, new Identifier(MODID, "aberration_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.ABERRATION, 0x150056, 0x826ccc));
		Registry.register(Registry.ITEM, new Identifier(MODID, "manaraw_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MANARAW, 0x181c59, 0x636b6d));
		Registry.register(Registry.ITEM, new Identifier(MODID, "mohast_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MOHAST, 0x477385, 0xacb7b7));
		Registry.register(Registry.ITEM, new Identifier(MODID, "sighthunter_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.SIGHT_HUNTER, 0x01293a, 0x808f95));
		Registry.register(Registry.ITEM, new Identifier(MODID, "craeken_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.CRAEKEN, 0xada7a2, 0xcee3e3));
		Registry.register(Registry.ITEM, new Identifier(MODID, "miraid_spawn_egg"),
				new DarkSpawnEgg(DarkWatersMobs.MIRAID, 0x5d5d6e, 0xd6d6d6));
		DarkWatersMobs.init();
	}
}
