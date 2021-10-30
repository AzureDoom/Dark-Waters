package mod.azure.darkwaters.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import mod.azure.darkwaters.DarkWatersMod;

@Config(name = DarkWatersMod.MODID)
public class DarkWatersConfig implements ConfigData {

	@ConfigEntry.Gui.CollapsibleObject
	public Spawning spawning = new Spawning();

	@ConfigEntry.Gui.CollapsibleObject
	public Stats stats = new Stats();

	public static class Spawning {
		public boolean require_storm_to_spawn = true;

		public int aberration_spawnweight = 10;
		public int aberration_spawnmin = 1;
		public int aberration_spawnmax = 1;
		
		public int mohast_spawnweight = 10;
		public int mohast_spawnmin = 3;
		public int mohast_spawnmax = 10;
	}

	public static class Stats {
		public double aberration_health = 25;
		public double aberration_attack_damage = 4;
		public int aberration_exp = 5;
	
		public double mohast_health = 25;
		public double mohast_attack_damage = 4;
		public int mohast_exp = 5;
	}

}
