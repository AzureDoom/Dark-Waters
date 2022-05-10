package mod.azure.darkwaters.entity;

import java.util.List;

import mod.azure.darkwaters.DarkWatersMod;
import mod.azure.darkwaters.util.DarkWatersSounds;
import net.minecraft.entity.Entity;
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
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.AbstractRandom;
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

public class MiraidHallucinationEntity extends BaseWaterEntity implements IAnimatable, IAnimationTickable {

	private AnimationFactory factory = new AnimationFactory(this);

	public MiraidHallucinationEntity(EntityType<? extends BaseWaterEntity> entityType, World world) {
		super(entityType, world);
		this.experiencePoints = 5;
	}

	public static DefaultAttributeContainer.Builder createMobAttributes() {
		return BaseWaterEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 70.0D)
				.add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 7.0D);
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
		data.addAnimationController(
				new AnimationController<MiraidHallucinationEntity>(this, "controller", 4, this::predicate));
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
		return DarkWatersSounds.MIRAD_HUM;
	}

	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_GENERIC_DEATH;
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return DarkWatersSounds.MIRAD_HURT;
	}

	@Override
	public int tickTimer() {
		return age;
	}

	@Override
	public void tick() {
		super.tick();
		float q = 50.0F;
		int k = MathHelper.floor(this.getX() - (double) q - 1.0D);
		int l = MathHelper.floor(this.getX() + (double) q + 1.0D);
		int t = MathHelper.floor(this.getY() - (double) q - 1.0D);
		int u = MathHelper.floor(this.getY() + (double) q + 1.0D);
		int v = MathHelper.floor(this.getZ() - (double) q - 1.0D);
		int w = MathHelper.floor(this.getZ() + (double) q + 1.0D);
		List<Entity> list = this.world.getOtherEntities(this,
				new Box((double) k, (double) t, (double) v, (double) l, (double) u, (double) w));
		for (int x = 0; x < list.size(); ++x) {
			Entity entity = (Entity) list.get(x);
			if (entity instanceof MiraidEntity) {
				this.setTextureState(((MiraidEntity) entity).isAttacking() ? true : false);
			}
		}
	}

	@SuppressWarnings("deprecation")
	public static boolean canSpawnInDarkWater(EntityType<MiraidHallucinationEntity> type, WorldAccess world,
			SpawnReason reason, BlockPos pos, AbstractRandom random) {
		if (pos.getY() > 45 && pos.getY() < world.getSeaLevel() && ((World) world).isThundering()
				&& DarkWatersMod.config.spawning.require_storm_to_spawn == true) {
			return ((World) world).isThundering() && world.getFluidState(pos).isIn(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL && world.getBiome(pos).isIn(BiomeTags.IS_OCEAN);
		} else if (DarkWatersMod.config.spawning.require_storm_to_spawn == false) {
			return world.getBiome(pos).isIn(BiomeTags.IS_OCEAN) && world.getFluidState(pos).isIn(FluidTags.WATER)
					&& world.getDifficulty() != Difficulty.PEACEFUL;
		} else {
			return false;
		}
	}

}
