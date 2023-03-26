package mod.azure.darkwaters.entity.helper;

import java.util.Map;

public enum AttackType {
	NONE(GenericAttackType.NONE), ATTACK(GenericAttackType.ATTACK), RUSH_ATTACK(GenericAttackType.RUSH_ATTACK), GRAB(GenericAttackType.GRAB), GRAB_ATTACK(GenericAttackType.GRAB_ATTACK), BITE(GenericAttackType.BITE), ENGULF(GenericAttackType.ENGULF);

	public static final Map<AttackType, String> animationMappings = Map.ofEntries(Map.entry(ATTACK, "attack"), Map.entry(RUSH_ATTACK, "rush_attack"), Map.entry(GRAB, "grab"), Map.entry(GRAB_ATTACK, "grab_attack"), Map.entry(BITE, "bite"), Map.entry(ENGULF, "engulf_begin"));

	public final GenericAttackType genericAttackType;

	AttackType(GenericAttackType genericAttackType) {
		this.genericAttackType = genericAttackType;
	}

}
