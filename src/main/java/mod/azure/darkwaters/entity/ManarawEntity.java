package mod.azure.darkwaters.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;

public class ManarawEntity extends BaseWaterEntity {

	public ManarawEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = 20;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 10.0D);
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
