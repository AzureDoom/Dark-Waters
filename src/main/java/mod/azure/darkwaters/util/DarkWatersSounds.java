package mod.azure.darkwaters.util;

import mod.azure.darkwaters.DarkWatersMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;

public class DarkWatersSounds {

	public static SoundEvent ABBERATION_AGGRO;
	public static SoundEvent ABBERATION_AMBIENT;
	public static SoundEvent ABBERATION_ATTACK;
	public static SoundEvent ABBERATION_HURT;

	public static SoundEvent CRAEKEN_ATTACK1;
	public static SoundEvent CRAEKEN_ATTACK2;
	public static SoundEvent CRAEKEN_HURT1;
	public static SoundEvent CRAEKEN_HURT2;

	public static SoundEvent MANARAW_AGGRO1;
	public static SoundEvent MANARAW_AGGRO2;
	public static SoundEvent MANARAW_AMBIENT1;
	public static SoundEvent MANARAW_AMBIENT2;
	public static SoundEvent MANARAW_ATTACK;
	public static SoundEvent MANARAW_HURT;

	public static SoundEvent MIRAD_HUM;
	public static SoundEvent MIRAD_AMBIENT;
	public static SoundEvent MIRAD_HURT;

	public static SoundEvent MOHAST_AMBIENT1;
	public static SoundEvent MOHAST_AMBIENT2;
	public static SoundEvent MOHAST_ATTACK_HURT;

	public static SoundEvent SLIGHTHUNTER_AGGRO;
	public static SoundEvent SLIGHTHUNTER_AMBIENT1;
	public static SoundEvent SLIGHTHUNTER_AMBIENT2;
	public static SoundEvent SLIGHTHUNTER_ATTACK_HURT;

	public static SoundEvent STORM_ABIENT1;
	public static SoundEvent STORM_ABIENT2;
	public static SoundEvent STORM_ABIENT3;
	public static SoundEvent STORM_BEGINNING1;
	public static SoundEvent STORM_BEGINNING2;
	public static SoundEvent STORM_END;

	static SoundEvent sound(String id) {
		SoundEvent sound = SoundEvent.createVariableRangeEvent(DarkWatersMod.modResource(id));
		Registry.register(BuiltInRegistries.SOUND_EVENT, DarkWatersMod.modResource(id), sound);
		return sound;
	}
	
	public static void init() {
		ABBERATION_AGGRO = sound("darkwaters.abberation_aggro");
		ABBERATION_AMBIENT = sound("darkwaters.abberation_ambient");
		ABBERATION_ATTACK = sound("darkwaters.abberation_attack");
		ABBERATION_HURT = sound("darkwaters.abberation_hurt");
		CRAEKEN_ATTACK1 = sound("darkwaters.craeken_attack");
		CRAEKEN_ATTACK2 = sound("darkwaters.craeken_attack2");
		CRAEKEN_HURT1 = sound("darkwaters.craeken_hurt1");
		CRAEKEN_HURT2 = sound("darkwaters.craeken_hurt2");
		MANARAW_AGGRO1 = sound("darkwaters.manaraw_aggro");
		MANARAW_AGGRO2 = sound("darkwaters.manaraw_aggro2");
		MANARAW_AMBIENT1 = sound("darkwaters.manaraw_ambient1");
		MANARAW_AMBIENT2 = sound("darkwaters.manaraw_ambient2");
		MANARAW_ATTACK = sound("darkwaters.manaraw_attack");
		MANARAW_HURT = sound("darkwaters.manaraw_hurt");
		MIRAD_HUM = sound("darkwaters.hallucination_hum");
		MIRAD_AMBIENT = sound("darkwaters.miraid_aggroambient");
		MIRAD_HURT = sound("darkwaters.miraid_hallucination_hurt");
		MOHAST_AMBIENT1 = sound("darkwaters.mohast_ambient1");
		MOHAST_AMBIENT2 = sound("darkwaters.mohast_ambient2");
		MOHAST_ATTACK_HURT = sound("darkwaters.mohast_hurt_attack");
		SLIGHTHUNTER_AGGRO = sound("darkwaters.sighthunter_aggro");
		SLIGHTHUNTER_AMBIENT1 = sound("darkwaters.sighthunter_aggroambient");
		SLIGHTHUNTER_AMBIENT2 = sound("darkwaters.sighthunter_aggroambient2");
		SLIGHTHUNTER_ATTACK_HURT = sound("darkwaters.sighthunter_attack_hurt");
		STORM_ABIENT1 = sound("darkwaters.storm_ambient1");
		STORM_ABIENT2 = sound("darkwaters.storm_ambient2");
		STORM_ABIENT3 = sound("darkwaters.storm_ambient3");
		STORM_BEGINNING1 = sound("darkwaters.storm_beginning");
		STORM_BEGINNING2 = sound("darkwaters.storm_beginning2");
		STORM_END = sound("darkwaters.storm_end");
	}
}
