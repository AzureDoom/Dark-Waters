package mod.azure.darkwaters.entity;

import mod.azure.darkwaters.config.DarkWatersConfig;
import mod.azure.darkwaters.entity.ai.goals.WaterAttackGoal;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BiomeTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.IAnimationTickable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class AberrationEntity extends BaseWaterEntity implements IAnimatable, IAnimationTickable {

	private AnimationFactory factory = new AnimationFactory(this);

	public AberrationEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = DarkWatersConfig.aberration_exp;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes()
				.add(EntityAttributes.GENERIC_MAX_HEALTH, DarkWatersConfig.aberration_health)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, DarkWatersConfig.aberration_attack_damage);
	}

	public <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
		if (event.isMoving()) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("moving", true));
			return PlayState.CONTINUE;
		}
		event.getController().setAnimation(new AnimationBuilder().addAnimation("idle", true));
		return PlayState.CONTINUE;
	}

	public <E extends IAnimatable> PlayState attack(AnimationEvent<E> event) {
		if (this.dataTracker.get(STATE) == 1 && !(this.dead || this.getHealth() < 0.01 || this.isDead())) {
			event.getController().setAnimation(new AnimationBuilder().addAnimation("attack", true));
			return PlayState.CONTINUE;
		}
		return PlayState.STOP;
	}

	@Override
	public void registerControllers(AnimationData data) {
		data.addAnimationController(new AnimationController<AberrationEntity>(this, "controller", 0, this::predicate));
		data.addAnimationController(new AnimationController<AberrationEntity>(this, "controller1", 0, this::attack));
	}

	@Override
	public AnimationFactory getFactory() {
		return this.factory;
	}

	@Override
	protected void initGoals() {
		super.initGoals();
		this.goalSelector.add(1, new WaterAttackGoal(this, 1, true, false));
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return DarkWatersSounds.ABBERATION_AMBIENT;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GENERIC_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return DarkWatersSounds.ABBERATION_HURT;
	}

	@Override
	public int tickTimer() {
		return age;
	}

	@SuppressWarnings("deprecation")
	public static boolean canSpawnInDarkWater(EntityType<AberrationEntity> type, WorldAccess world, SpawnReason reason,
			BlockPos pos, Random random) {
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() && ((World) world).isThundering()
				&& DarkWatersConfig.require_storm_to_spawn == true) {
			return ((World) world).isThundering() && world.getFluidState(pos).isIn(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL && world.getBiome(pos).isIn(BiomeTags.IS_OCEAN);
		} else if (DarkWatersConfig.require_storm_to_spawn == false) {
			return world.getBiome(pos).isIn(BiomeTags.IS_OCEAN) && world.getFluidState(pos).isIn(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL;
		} else {
			return false;
		}
	}

}
