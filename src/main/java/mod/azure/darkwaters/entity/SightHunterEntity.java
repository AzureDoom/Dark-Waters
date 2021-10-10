package mod.azure.darkwaters.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class SightHunterEntity extends BaseWaterEntity {

	public SightHunterEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = 8;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 25.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 6.0D);
	}

	@Override
	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		return PlayState.CONTINUE;
	}

	@Override
	protected void initGoals() {
		super.initGoals();

	}

}
