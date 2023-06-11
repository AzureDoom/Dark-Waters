package mod.azure.darkwaters.config;

import mod.azure.azurelib.config.Config;
import mod.azure.azurelib.config.Configurable;
import mod.azure.darkwaters.DarkWatersMod;

@Config(id = DarkWatersMod.MODID)
public class DarkWatersConfig {

	@Configurable
	public boolean require_storm_to_spawn = true;
	@Configurable
	public int aberration_spawnweight = 10;
	@Configurable
	public int mohast_spawnweight = 10;
	@Configurable
	public int miraid_spawnweight = 1;
	@Configurable
	public int craeken_spawnweight = 1;
	@Configurable
	public int manaraw_spawnweight = 1;
	@Configurable
	public int sighthunter_spawnweight = 1;

	@Configurable
	public double aberration_health = 25;
	@Configurable
	public double aberration_attack_damage = 4;
	@Configurable
	public int aberration_exp = 5;

	@Configurable
	public double mohast_health = 25;
	@Configurable
	public double mohast_attack_damage = 4;
	@Configurable
	public int mohast_exp = 5;

	@Configurable
	public double miraid_health = 70;
	@Configurable
	public double miraid_attack_damage = 7;
	@Configurable
	public int miraid_exp = 5;

	@Configurable
	public double craeken_health = 80;
	@Configurable
	public double craeken_attack_damage = 6;
	@Configurable
	public int craeken_exp = 5;

	@Configurable
	public double manaraw_health = 100;
	@Configurable
	public double manaraw_attack_damage = 10;
	@Configurable
	public int manaraw_exp = 20;

	@Configurable
	public double sighthunter_health = 25;
	@Configurable
	public double sighthunter_attack_damage = 6;
	@Configurable
	public int sighthunter_exp = 8;

}
