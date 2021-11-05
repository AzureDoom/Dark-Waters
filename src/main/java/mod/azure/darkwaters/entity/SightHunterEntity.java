package mod.azure.darkwaters.entity;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class SightHunterEntity extends BaseWaterEntity implements IAnimatable, IAnimationTickable {

	private AnimationFactory factory = new AnimationFactory(this);

	public SightHunterEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = DarkWatersMod.config.stats.sighthunter_exp;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, DarkWatersMod.config.stats.sighthunter_health)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, DarkWatersMod.config.stats.sighthunter_attack_damage);
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("moving", true));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<SightHunterEntity>(this, "controller", 0, this::predicate));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void initGoals() {
		super.initGoals();

	}

	@Override
	protected SoundEvent getAmbientSound() {
		return r == 1 ? DarkWatersSounds.SLIGHTHUNTER_AMBIENT1 : DarkWatersSounds.SLIGHTHUNTER_AMBIENT2;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GENERIC_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return DarkWatersSounds.SLIGHTHUNTER_ATTACK_HURT;
	}

	@Override
	public int tickTimer() {
		return age;
	}

}
