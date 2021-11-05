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
		public int mohast_spawnweight = 10;
		public int miraid_spawnweight = 1;
		public int craeken_spawnweight = 1;
		public int manaraw_spawnweight = 1;
		public int sighthunter_spawnweight = 1;
	}

	public static class Stats {
		public double aberration_health = 25;
		public double aberration_attack_damage = 4;
		public int aberration_exp = 5;
	
		public double mohast_health = 25;
		public double mohast_attack_damage = 4;
		public int mohast_exp = 5;
	
		public double miraid_health = 70;
		public double miraid_attack_damage = 7;
		public int miraid_exp = 5;
	
		public double craeken_health = 80;
		public double craeken_attack_damage = 6;
		public int craeken_exp = 5;
	
		public double manaraw_health = 100;
		public double manaraw_attack_damage = 10;
		public int manaraw_exp = 20;
	
		public double sighthunter_health = 25;
		public double sighthunter_attack_damage = 6;
		public int sighthunter_exp = 8;
	}

}
