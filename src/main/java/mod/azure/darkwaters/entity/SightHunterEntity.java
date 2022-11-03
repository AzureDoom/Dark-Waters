package mod.azure.darkwaters.entity;

import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.entity.ai.goals.WaterAttackGoal;
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
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class SightHunterEntity extends BaseWaterEntity implements IAnimatable, IAnimationTickable {

	private AnimationFactory factory = GeckoLibUtil.createFactory(this);

	public SightHunterEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = DarkWatersConfig.sighthunter_exp;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, DarkWatersConfig.sighthunter_health)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, DarkWatersConfig.sighthunter_attack_damage);
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("moving", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", EDefaultLoopTypes.LOOP));
		return PlayState.CONTINUE;
	}

	public <E extends IAnimatable> PlayState attack(AnimationEvent<E> event) {
		if (this.dataTracker.get(STATE) == 1 && !(this.dead || this.getHealth() < 0.01 || this.isDead())) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", EDefaultLoopTypes.LOOP));
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<SightHunterEntity>(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController<SightHunterEntity>(this, "controller1", 0, this::attack));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(1, new WaterAttackGoal(this, 1, true, true));
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
