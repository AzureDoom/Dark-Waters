package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class DarkWatersSounds {

	public static SoundEvent ABBERATION_AGGRO = of("darkwaters.abberation_aggro");
	public static SoundEvent ABBERATION_AMBIENT = of("darkwaters.abberation_ambient");
	public static SoundEvent ABBERATION_ATTACK = of("darkwaters.abberation_attack");
	public static SoundEvent ABBERATION_HURT = of("darkwaters.abberation_hurt");

	public static SoundEvent CRAEKEN_ATTACK1 = of("darkwaters.craeken_attack");
	public static SoundEvent CRAEKEN_ATTACK2 = of("darkwaters.craeken_attack2");
	public static SoundEvent CRAEKEN_HURT1 = of("darkwaters.craeken_hurt1");
	public static SoundEvent CRAEKEN_HURT2 = of("darkwaters.craeken_hurt2");

	public static SoundEvent MANARAW_AGGRO1 = of("darkwaters.manaraw_aggro");
	public static SoundEvent MANARAW_AGGRO2 = of("darkwaters.manaraw_aggro2");
	public static SoundEvent MANARAW_AMBIENT1 = of("darkwaters.manaraw_ambient1");
	public static SoundEvent MANARAW_AMBIENT2 = of("darkwaters.manaraw_ambient2");
	public static SoundEvent MANARAW_ATTACK = of("darkwaters.manaraw_attack");
	public static SoundEvent MANARAW_HURT = of("darkwaters.manaraw_hurt");

	public static SoundEvent MIRAD_HUM = of("darkwaters.hallucination_hum");
	public static SoundEvent MIRAD_AMBIENT = of("darkwaters.miraid_aggroambient");
	public static SoundEvent MIRAD_HURT = of("darkwaters.miraid_hallucination_hurt");

	public static SoundEvent MOHAST_AMBIENT1 = of("darkwaters.mohast_ambient1");
	public static SoundEvent MOHAST_AMBIENT2 = of("darkwaters.mohast_ambient2");
	public static SoundEvent MOHAST_ATTACK_HURT = of("darkwaters.mohast_hurt_attack");

	public static SoundEvent SLIGHTHUNTER_AGGRO = of("darkwaters.sighthunter_aggro");
	public static SoundEvent SLIGHTHUNTER_AMBIENT1 = of("darkwaters.sighthunter_aggroambient");
	public static SoundEvent SLIGHTHUNTER_AMBIENT2 = of("darkwaters.sighthunter_aggroambient2");
	public static SoundEvent SLIGHTHUNTER_ATTACK_HURT = of("darkwaters.sighthunter_attack_hurt");

	public static SoundEvent STORM_ABIENT1 = of("darkwaters.storm_ambient1");
	public static SoundEvent STORM_ABIENT2 = of("darkwaters.storm_ambient2");
	public static SoundEvent STORM_ABIENT3 = of("darkwaters.storm_ambient3");
	public static SoundEvent STORM_BEGINNING1 = of("darkwaters.storm_beginning");
	public static SoundEvent STORM_BEGINNING2 = of("darkwaters.storm_beginning2");
	public static SoundEvent STORM_END = of("darkwaters.storm_end");

	static SoundEvent of(String id) {
		SoundEvent sound = new SoundEvent(new Identifier(DarkWatersMod.MODID, id));
		Registry.register(Registry.SOUND_EVENT, new Identifier(DarkWatersMod.MODID, id), sound);
		return sound;
	}
}
