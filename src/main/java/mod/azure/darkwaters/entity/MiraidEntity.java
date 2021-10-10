package mod.azure.darkwaters.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class MiraidEntity extends BaseWaterEntity {

	public MiraidEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = 5;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D);
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
