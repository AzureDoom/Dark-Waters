package mod.azure.darkwaters.config;

import mod.azure.azurelib.config.Config;
import mod.azure.azurelib.config.Configurable;
import mod.azure.darkwaters.DarkWatersMod;

@Config(id = DarkWatersMod.MODID)
public class DarkWatersConfig {

	@Configurable
	@Configurable.Synchronized
	public boolean require_storm_to_spawn = true;
	
    @Configurable
    public AberrationConfigs aberrationconfigs = new AberrationConfigs();

    public static class AberrationConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int aberration_spawnweight = 10;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double aberration_health = 25;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double aberration_attack_damage = 4;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int aberration_exp = 5;
    }
	
    @Configurable
    public CraekenConfigs craekenconfigs = new CraekenConfigs();

    public static class CraekenConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int craeken_spawnweight = 1;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double craeken_health = 80;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double craeken_attack_damage = 6;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int craeken_exp = 5;
    }
	
    @Configurable
    public ManarawConfigs manarawconfigs = new ManarawConfigs();

    public static class ManarawConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int manaraw_spawnweight = 1;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double manaraw_health = 100;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double manaraw_attack_damage = 10;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int manaraw_exp = 20;
    }
	
    @Configurable
    public MiraidConfigs miraidconfigs = new MiraidConfigs();

    public static class MiraidConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int miraid_spawnweight = 1;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double miraid_health = 70;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double miraid_attack_damage = 7;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int miraid_exp = 5;
    	
    }
	
    @Configurable
    public MohastConfigs mohastconfigs = new MohastConfigs();

    public static class MohastConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int mohast_spawnweight = 10;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double mohast_health = 25;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double mohast_attack_damage = 4;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int mohast_exp = 5;
    }
	
    @Configurable
    public SlighthunterConfigs slighthunterconfigs = new SlighthunterConfigs();

    public static class SlighthunterConfigs {
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int sighthunter_spawnweight = 1;

    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double sighthunter_health = 25;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.DecimalRange(min = 1)
    	public double sighthunter_attack_damage = 6;
    	
    	@Configurable
    	@Configurable.Synchronized
    	@Configurable.Range(min = 1)
    	public int sighthunter_exp = 8;
    }

}
