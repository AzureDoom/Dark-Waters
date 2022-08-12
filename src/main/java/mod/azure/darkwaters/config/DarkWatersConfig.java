package mod.azure.darkwaters.config;

public class DarkWatersConfig extends CustomMidnightConfig {

	@Entry
	public static boolean require_storm_to_spawn = true;
	@Entry
	public static int aberration_spawnweight = 10;
	@Entry
	public static int mohast_spawnweight = 10;
	@Entry
	public static int miraid_spawnweight = 1;
	@Entry
	public static int craeken_spawnweight = 1;
	@Entry
	public static int manaraw_spawnweight = 1;
	@Entry
	public static int sighthunter_spawnweight = 1;

	@Entry
	public static double aberration_health = 25;
	@Entry
	public static double aberration_attack_damage = 4;
	@Entry
	public static int aberration_exp = 5;

	@Entry
	public static double mohast_health = 25;
	@Entry
	public static double mohast_attack_damage = 4;
	@Entry
	public static int mohast_exp = 5;

	@Entry
	public static double miraid_health = 70;
	@Entry
	public static double miraid_attack_damage = 7;
	@Entry
	public static int miraid_exp = 5;

	@Entry
	public static double craeken_health = 80;
	@Entry
	public static double craeken_attack_damage = 6;
	@Entry
	public static int craeken_exp = 5;

	@Entry
	public static double manaraw_health = 100;
	@Entry
	public static double manaraw_attack_damage = 10;
	@Entry
	public static int manaraw_exp = 20;

	@Entry
	public static double sighthunter_health = 25;
	@Entry
	public static double sighthunter_attack_damage = 6;
	@Entry
	public static int sighthunter_exp = 8;

}
